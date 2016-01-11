# jaxws-example
@author: akursat

@website: akursat.com

Technologies used
-------------------
Jax-Ws  
Hibernate  
Tomcat  
Mysql  

Introduction
-------------------
Simple example of consuming wsdl webservice.   
I do not use Jpa. I only use pure Hibernate for ORM.
I create maven project with sub-modules (web service and client)

Building
-------------------
You must edit hibernate.cfg.xml. You must change [username] and [password] to your username and password of the database. The default username is [root], password is [akursat].  
Here is my SQL query which I use in the project. 
```
CREATE TABLE users ( 
username character varying(50) NOT NULL, 
password character varying(50) NOT NULL, 
email character varying(50) NOT NULL, 
birthday date, 
sex smallint, 
enabled boolean, 
CONSTRAINT users_pkey PRIMARY KEY (username) 
)

CREATE TABLE authorities ( 
username character varying(50) NOT NULL, 
authority character varying(50) NOT NULL, 
CONSTRAINT fk_authorities_users FOREIGN KEY (username) 
REFERENCES users (username) 
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION 
)
```
Deploying
-------------------
You can run tomcat, jboss etc. You just need to put your war file in webapps and then start your server.  
Run java files sequentially: WebServicePublisher.java, TestClient.java 
