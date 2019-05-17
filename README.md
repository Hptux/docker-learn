# docker-learn
learn how to use docker in my development

# install docker on ubuntu
参考[docker官方手册]进行安装
####准备

       64位系统，16.04 或者更新的版本
####删除旧版本

        $ sudo apt-get remove docker docker-engine docker.io containerd runc
        
如没有安装，apt会返回没有包需要删除，这是正常的。
相关内容会被保留`/var/lib/docker/`， 包含镜像，容器，网络等包

####通过docker软件源安装
######更新软件源：

        $ sudo apt-get update
        
######安装相关包使apt可以支持https软件源：

        $ sudo apt-get install \
          apt-transport-https \
          ca-certificates \
          curl \
          gnupg-agent \
          software-properties-common
          
######添加docker官方GPG key：

        $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
        
######校验key，特征码`9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88`

        $ sudo apt-key fingerprint 0EBFCD88  
        pub   rsa4096 2017-02-22 [SCEA]
              9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88
        uid   [unknown] Docker Release (CE deb) <docker@docker.com>
        sub   rsa4096 2017-02-22 [S]
        
######使用以下命令设置软件源：
    (linux mint等发行版根据版本寻找对应ubuntu版本名，替换`$(lsb_release -cs)`)
        
        $ sudo add-apt-repository \
         "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
         $(lsb_release -cs) \
         stable"
         
######安装 DOCKER CE 最新版本：
        
        $ sudo apt-get update
        $ sudo apt-get install docker-ce docker-ce-cli containerd.io
        
######安装 DOCKER CE 指定版本：
        
        $ apt-cache madison docker-ce
      
        docker-ce | 5:18.09.1~3-0~ubuntu-xenial | https://download.docker.com/linux/ubuntu  xenial/stable amd64 Packages
        docker-ce | 5:18.09.0~3-0~ubuntu-xenial | https://download.docker.com/linux/ubuntu  xenial/stable amd64 Packages
        docker-ce | 18.06.1~ce~3-0~ubuntu       | https://download.docker.com/linux/ubuntu  xenial/stable amd64 Packages
        docker-ce | 18.06.0~ce~3-0~ubuntu       | https://download.docker.com/linux/ubuntu  xenial/stable amd64 Packages
        ...

        使用第二列`5:18.09.1~3-0~ubuntu-xenial`替换下面命令中的`<VERSION_STRING>`：

        $ sudo apt-get install docker-ce=<VERSION_STRING> docker-ce-cli=<VERSION_STRING> containerd.io

验证安装成功：

        $ sudo docker run hello-world

###安装完成后的一些处理
#####使非root用户不用sudo命令也可以管理docker

    添加docker用户组：        
        $ sudo groupadd docker
    将当前用户添加至docker用户组：        
        $ sudo usermod -aG docker $USER
    注销当前用户重新登录，或者重启电脑
    验证操作成功：        
        $ docker run hello-world



[docker手册]: https://docs.docker.com/install/linux/docker-ce/ubuntu/