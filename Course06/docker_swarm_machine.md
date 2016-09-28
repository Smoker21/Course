使用 docker toolkit 測試 docker-swarm 
====================================

http://www.projectcalico.org/getting-started/docker/

    docker-machine --help 

這不是說明文件，只是自己參考用的而已，原廠文件如後 <https://github.com/docker/swarm/blob/master/docs/install-w-machine.md>

1. 建立 swarm token , 找一台有 docker 的機器做這件事情.. ，中間的東西不要管他，注意最後輸出的字串，那個就是 token : 9f69d81d12bc5d554496da8a967e50a3, --rm 的意思是跑完就把 container 幹掉 

	```
    $ docker run --rm swarm create
    Unable to find image 'swarm:latest' locally
    latest: Pulling from library/swarm
    28d9e4361d1b: Pull complete 
    d6d187f38730: Pull complete 
    009ef5e8bff4: Pull complete 
    2c15723f36b9: Pull complete 
    207689e26fb2: Pull complete 
    efff5eea5f34: Pull complete 
    7823bc4f62a4: Pull complete 
    207e8b983242: Already exists 
    library/swarm:latest: The image you are pulling has been verified. Important: image verification is a tech preview feature and should not be relied on to provide security.
    Digest: sha256:fbca254e741911b4e2028072c828845fbd3200e72392a2d5aab8cf8d427af816
    Status: Downloaded newer image for swarm:latest
    9f69d81d12bc5d554496da8a967e50a3
	```
    
1. 建立 Docker machine

    ```
    $ docker-machine create -d virtualbox \
      --virtualbox-memory 2048 \
      --swarm \
      --swarm-master \
      --swarm-discovery token://9f69d81d12bc5d554496da8a967e50a3
      swarm00 
    ```

2.  檢查 Virtual box 狀態, 可以開 virtual box 檢查，會看到 swarm00 ，也可以使用 docker-machine ls , 底下可以看到 IP ，注意 SWARM  狀態
    
    ```
    $ docker-machine ls
    NAME      ACTIVE   DRIVER       STATE     URL                         SWARM
    default   *        virtualbox   Running   tcp://192.168.99.100:2376
    swarm00            virtualbox   Running   tcp://192.168.99.104:2376   swarm00 (master)
    ```
    
3. 使用連線軟體進入 virtual box 192.168.99.104 , docker ps 看狀況

    ```
    docker@swarm00:~$ docker ps
    CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                              NAMES
    80ad3bddc980        swarm:latest        "/swarm join --advert"   3 minutes ago       Up 3 minutes        2375/tcp                           swarm-agent
    f89ef362cad8        swarm:latest        "/swarm manage --tlsv"   3 minutes ago       Up 3 minutes        0.0.0.0:3376->3376/tcp, 2375/tcp   swarm-agent-master
    ```

4. 建立下一台 docker-machine ，當成 swarm slave ，做完同時做 docker-machine ls ，然後連進去看看狀態
    
    ```
    $ docker-machine create -d virtualbox --virtualbox-memory 2048 --swarm --swarm-discovery token://9f69d81d12bc5d554496da8a967e50a3 swarm01
    Creating VirtualBox VM...
    Creating SSH key...
    Starting VirtualBox VM...
    Starting VM...
    To see how to connect Docker to this machine, run: c:\Program Files\Docker Toolbox\docker-machine.exe env swarm01
    ```
    
5. 要試試 cluster ，當然還要下一台 swarm02 ，可是不用再打一次了吧.. 

6. 這行很重要.. 不下就做不動了, 這是設定環境變數用的，做完用 docker info 應該會看到底下資訊，起了四個 container ，三個 images. 總共三台 VM 在 cluster 裡面
    
    ```
    $ eval $(docker-machine env --swarm swarm00)
    $ docker info
    Containers: 4
    Images: 3
    Role: primary
    Strategy: spread
    Filters: affinity, health, constraint, port, dependency
    Nodes: 3
    swarm00: 192.168.99.104:2376
    └ Containers: 2
    └ Reserved CPUs: 0 / 1
    └ Reserved Memory: 0 B / 2.054 GiB
    └ Labels: executiondriver=native-0.2, kernelversion=4.0.9-boot2docker, operatingsystem=Boot2Docker 1.8.1 (TCL 6.3); master : 7f12e95 - Thu Aug 13 03:24:56 UTC 2015, provider=virtualbox, storagedriver=aufs
    swarm01: 192.168.99.105:2376
    └ Containers: 1
    └ Reserved CPUs: 0 / 1
    └ Reserved Memory: 0 B / 1.022 GiB
    └ Labels: executiondriver=native-0.2, kernelversion=4.0.9-boot2docker, operatingsystem=Boot2Docker 1.8.1 (TCL 6.3); master : 7f12e95 - Thu Aug 13 03:24:56 UTC 2015, provider=virtualbox, storagedriver=aufs
    swarm02: 192.168.99.106:2376
    └ Containers: 1
    └ Reserved CPUs: 0 / 1
    └ Reserved Memory: 0 B / 1.022 GiB
    └ Labels: executiondriver=native-0.2, kernelversion=4.0.9-boot2docker, operatingsystem=Boot2Docker 1.8.1 (TCL 6.3); master : 7f12e95 - Thu Aug 13 03:24:56 UTC 2015, provider=virtualbox, storagedriver=aufs
    CPUs: 3
    Total Memory: 4.097 GiB
    Name: f89ef362cad8
    ```

6. 研究一下剛剛的環境變數到底是哪些鬼, 注意，這裡下的對象是 master 的 swarm00 
    
    ```
    $docker-machine env swarm00 
    export DOCKER_TLS_VERIFY="1"
    export DOCKER_HOST="tcp://192.168.99.104:2376"
    export DOCKER_CERT_PATH="C:\{your_path}\.docker\machine\machines\swarm00"
    export DOCKER_MACHINE_NAME="swarm00"
    # Run this command to configure your shell:
    # eval "$(c:\Program Files\Docker Toolbox\docker-machine.exe env swarm00)"
    ```
    我個人不喜歡 windows docker tool 的 terminal ，把這個東西改一改，丟到任一台新建的 docker machine 上面，就可以從那台機器對整個 cluster 下 docker 指令了
    
8. 