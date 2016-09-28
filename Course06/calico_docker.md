Docker 和 Calico Layer 3 switch 
===============================

無責任宣言：這是單純個人用筆記，參考請自己小心，使用環境是 docker-machine 做出來的 VM.. 指令有不同請自己調整

玩這個以前要先設定好 etcd 請參考這邊 etcd 筆記

原廠文件連結 <http://www.projectcalico.org/getting-started/docker/>

    $wget http://projectcalico.org/latest/calicoctl 
    $chmod +x calicoctl 

先看一下自己的 ip 
    
    $ip addr 
    
然後開