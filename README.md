# tk mybatis和druid的多数据源整合demo

本地新建durid1和druid2两个数据库 

分别运行以下查询 

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

在durid1的user表中随便插入几条数据

运行程序
  
