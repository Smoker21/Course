##Core OS 安裝筆記
----

- [原廠文件看這邊](https://coreos.com/os/docs/latest/)
- [這邊使用 Vagrant](https://coreos.com/os/docs/latest/booting-on-vagrant.html) , 沒裝 Vagrant 的請參考[這邊](https://docs.vagrantup.com/v2/installation/)
- [這邊使用 VirtualBox](https://www.virtualbox.org/), 請參考網址去安裝
- 過程中需要使用 ssh client ，windows 用戶如果有使用 git ，請增加 PATH 變數 {GIT INSTALL PATH}/bin，開 cmd shell 的時候試試看 ssh 有沒有作用，如果沒有 git ，裝一個吧

###Core OS Cluster 安裝
--------------------

- 按照文件做安裝

    ```bash    
    git clone https://github.com/coreos/coreos-vagrant.git
    cd coreos-vagrant
    vagrant up 
    ```
    
    這樣會起一個標準的 virtual box VM

-  因為 Vagrant 的 CoreOS VM 預設使用 vagrant 的 insecure key 來做登入，位置放在 C:\Users\{username}\.vagrant.d\insecure_private_key ，如果你跟我一樣使用 SecureCRT，需要把 Session / properties / connection / SSH2 / Authentication  裡面除了 public key 之外的選項都拿掉。然後在 public key 選項的設定，選進 vagrant 的 private key. **登入後預設 username 是 core** ，不用輸入密碼就可進去了

    - vagrant 使用簡單的 SSH + key 來做登入，這邊有個使用 putty 的參考 [http://www.vixual.net/blog/archives/190](http://www.vixual.net/blog/archives/190)，用 private key 代替密碼登入
    
- 進去之後會發現 etcd 那些都沒有設好，而且只有一台，玩 cluster 低消是三台.. 因此要重來過，使用以下指令幹掉 VM 

    ```
    vagrant status
    vagrant destroy
    ```
    
- 原本 coreos-vagrant 目錄下面有兩個檔案 config.rb.sample , user-data.sample ，請分別複製到 config.rb , user-data , [完整的 cloud config 文件](https://coreos.com/os/docs/latest/cloud-config.html) 
  
  1. 產生 etcd 用的 token 檔案. https://discovery.etcd.io/new?size={instance number} 要起三台，所以打這網址 https://discovery.etcd.io/new?size=3，噹過去之後可以得到一個 token ，抄到 user-data 裡面，把 discovery 的部分都換掉，文件說明說是每次使用 vagrant destroy 需要重新建立一個。etcd 使用這個 token 來做節點之間的溝通使用，如果裝的機器不能打打到 https://discovery.etcd.io 的話，那就需要自己建立一個，原始碼請參考[這裡](https://github.com/coreos/discovery.etcd.io) ，懶得從原始碼開始搞，請參考這裡 [docker image](https://github.com/coreos/discovery.etcd.io)
  
    ```ruby
    coreos:
        etcd:
            # generate a new token for each unique cluster from https://discovery.etcd.io/new
            # WARNING: replace each time you 'vagrant destroy'
            #discovery: https://discovery.etcd.io/<token>
	       discovery: https://discovery.etcd.io/31c37d32e086573c1da1150b68ee12f3
            addr: $public_ipv4:4001
            peer-addr: $public_ipv4:7001
        etcd2:
            #generate a new token for each unique cluster from https://discovery.etcd.io/new
            discovery: https://discovery.etcd.io/31c37d32e086573c1da1150b68ee12f3
    ```
  
  2. 修改 config.rb 
  
    ```
    # Size of the CoreOS cluster created by Vagrant
    $num_instances=3
    ```

  3. 其實看得懂的話，可以直接修改 vagrantfile ，反正都是改 Ruby code. user-data 的部分注意 units 底下，那是定義 systemd  service 用的, [systemd 說明文件](https://coreos.com/docs/launching-containers/launching/getting-started-with-systemd/)
  
  4. 進 command shell 下 vagrant up ，應該會看到類似底下這些，注意 SSH 連線成功
  
    ```
    D:\vagrant\coreOS\coreos-vagrant>vagrant up
    Bringing machine 'core-01' up with 'virtualbox' provider...
    Bringing machine 'core-02' up with 'virtualbox' provider...
    Bringing machine 'core-03' up with 'virtualbox' provider...
    ==> core-01: Importing base box 'coreos-stable'...
    ==> core-01: Matching MAC address for NAT networking...
    ==> core-01: Checking if box 'coreos-stable' is up to date...
    ==> core-01: Setting the name of the VM: coreos-vagrant_core-01_1443501595942_46204
    ==> core-01: Clearing any previously set network interfaces...
    ==> core-01: Preparing network interfaces based on configuration...
        core-01: Adapter 1: nat
        core-01: Adapter 2: hostonly
    ==> core-01: Forwarding ports...
        core-01: 22 => 2222 (adapter 1)
    ==> core-01: Running 'pre-boot' VM customizations...
    ==> core-01: Booting VM...
    ==> core-01: Waiting for machine to boot. This may take a few minutes...
        core-01: SSH address: 127.0.0.1:2222
        core-01: SSH username: core
        core-01: SSH auth method: private key
        core-01: Warning: Connection timeout. Retrying...
  ```
    
  5. 啟動完成後，連線進去 core-01 下以下指令確認 fleet 和 etcd2 都有順利啟動    
    
    ```s    hell
    core@core-03 ~ $ etcdctl member list
    22fbd03b419f4c09: name=0a4e832b0daa40cd83abd5f3945bc2a9 peerURLs=http://172.17.8.103:2380 clientURLs=http://172.17.8.103:2379
    70219ffff625b660: name=8e1f5ff5b77e40229cfcbee82aa2c2fc peerURLs=http://172.17.8.101:2380 clientURLs=http://172.17.8.101:2379
    c756c6590deaa330: name=2a36d84ac1c143488ccea636ed5ac779 peerURLs=http://172.17.8.102:2380 clientURLs=http://172.17.8.102:2379
    ```
    
    ```shell
    core@core-03 ~ $ fleetctl list-machines
    MACHINE         IP              METADATA
    0a4e832b...     172.17.8.103    -
    2a36d84a...     172.17.8.102    -
    8e1f5ff5...     172.17.8.101    -
    ```
    
  6. 既然搞出 fleet 就要裝一個 docker service 來玩啊.. 先跑一下 docker run hello-world 
  
    ```shell
    ore@core-01 ~/system $ docker run hello-world
    Unable to find image 'hello-world:latest' locally
    latest: Pulling from hello-world
    535020c3e8ad: Pull complete 
    af340544ed62: Pull complete 
    Digest: sha256:a68868bfe696c00866942e8f5ca39e3e31b79c1e50feaee4ce5e28df2f051d5c
    Status: Downloaded newer image for hello-world:latest
    
    Hello from Docker.
    This message shows that your installation appears to be working correctly.

    To generate this message, Docker took the following steps:
    1. The Docker client contacted the Docker daemon.
    2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
    4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

    To try something more ambitious, you can run an Ubuntu container with:
    $ docker run -it ubuntu bash

    Share images, automate workflows, and more with a free Docker Hub account:
    https://hub.docker.com

    For more examples and ideas, visit:
    https://docs.docker.com/userguide/  
    ```
    
  7. 把這個動作搞成一個 fleet unit 
  
    ```shell 
    
    
  
  
  
    