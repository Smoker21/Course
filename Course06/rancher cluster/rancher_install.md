## Rancher Cluster 安裝筆記  ##
---

### Single Node Cluster mode ###

1. docker-toolbox install (ignore)
2. docker-machine start machine 裝一台 master rancher00, 三台 client
```shell
docker-machine create -d virtualbox --virtualbox-memory 2048 --virtualbox-boot2docker-url https://releases.rancher.co m/os/latest/rancheros.iso rancher00
docker-machine create -d virtualbox --virtualbox-memory 2048 --virtualbox-boot2docker-url https://releases.rancher.co m/os/latest/rancheros.iso rancher01
docker-machine create -d virtualbox --virtualbox-memory 2048 --virtualbox-boot2docker-url https://releases.rancher.co m/os/latest/rancheros.iso rancher02
docker-machine create -d virtualbox --virtualbox-memory 2048 --virtualbox-boot2docker-url https://releases.rancher.co m/os/latest/rancheros.iso rancher03
docker-machine ls
```
3. 使用 ssh terminal 連線進去 rancher os <br/>
key 存放的位置在 C:\Users\{username}\.docker\machine\machines\rancher00\id_rsa </br>
default user: docker , default password: 
整個設定的資料放在 config.json <br/>
rancherOS 和 boot2docker.iso 不一樣，使用 docker/tcuser 無法登入，一定要那把 id_rsa 的 key <br/>
當然，使用　docker-machine ssh rancher00 還是可以動

4. 啟動　rancher server
```shell
docker run -d --restart=unless-stopped -p 8080:8080 rancher/server
```

5. 






