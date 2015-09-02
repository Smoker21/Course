Mesos install book 
==================

Use bento/centos-6.7 image. 

1. Vagrant File segments

Master node
	
	config.vm.define "mesos_m01" do |mesos_m01|
		mesos_m01.vm.network "private_network", ip: "172.17.85.101"
		mesos_m01.vm.hostname="mesos-m01"
		mesos_m01.vm.provider :virtualbox do |vb|
			vb.gui = false
			vb.memory = "2048"
			vb.name="mesos_m01"
			vb.cpus = "2"
		end	
	end  

  
Slave node

  config.vm.define "mesos_s00" do |mesos_s00|
	mesos_s00.vm.network "private_network", ip: "172.17.95.100"
	mesos_s00.vm.hostname="mesos-s00"
    mesos_s00.vm.provider :virtualbox do |vb|
		vb.gui = false
        vb.memory = "2048"
		vb.name="mesos_s00"
        vb.cpus = "2"
    end	
  end  	

  
2. Mesos master install 

	sudo rpm -Uvh http://repos.mesosphere.io/el/6/noarch/RPMS/mesosphere-el-repo-6-2.noarch.rpm
	yum -y install mesos marathon 
	rpm -Uvh http://archive.cloudera.com/cdh4/one-click-install/redhat/6/x86_64/cloudera-cdh-4-0.x86_64.rpm
	yum -y install zookeeper
	
Setup server Id  (each server should be different)

	sudo zookeeper-server-initialize --myid=1

Edit /etc/zookeeper/conf/zoo.cfg, update the ip to your own. 

	server.1=172.17.85.100:2888:3888
	server.2=172.17.85.101:2888:3888
	
	
	