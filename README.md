conectarse a la base de datos h2:

http://localhost:8031/PrototypeSprintBoot/h2

EndPoint en Postman:

Get ReadApplication : http://localhost:8031/PrototypeSprintBoot/application/read/1
Get GetAsync : http://localhost:8031/PrototypeSprintBoot/application/hello-async?applicationId=1

Query para cambiar en base de datos:

update application 
set app_name ='BitBucket'
where application_id =1;