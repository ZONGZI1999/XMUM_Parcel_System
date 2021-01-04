# XMUM Parcel System

#### Introduction
This mode of operation in XMUM has several repercussions. If there is a wrong delivery or if a certain parcel is lost, it is difficult for both the students and the personnel at work to track down the problematic parcels. For students, they can only predict when the parcel will arrive.

To solve these problems, we have designed a parcel system for XMUM, this system can be used by XMUM parcel administrators, students, and teachers.

#### Development Team
Zheng Zongqi(SWE1809388 Team Leader)
Wang Yilin(SWE1809375)
HEE ZIXI(SWE1809649)

#### Github Link:
https://github.com/ZONGZI1999/XMUM_Parcel_System/

###### Visitors
Our system allows visitors or guests to search for specific parcels without logging in. However, this can be done only with tracking numbers.

###### Client 
The Client side of the system is mainly designed for students and lecturers of XMUM. The system is set up with four main functional blocks: “Parcel query”, “My parcel”, “Parcel pickup”, and “Parcel post”.

###### Management
The management side is mainly designed for the staff of XMUM to manage parcels. This part of the system is mainly divided into three functional blocks: “Package storage”, “Parcel management”, and “Parcel post”.

###### Before Running This Project
First, you should change the configuration in the path `src/main/resources/application.properties`

Second, change `spring.datasource.password` to your database password.

Third, reload maven to import all package we need.

Now, you can run it.

After running, you could access `http://localhost:8899` 

This is the default user:

(Login name, Password):

 **ZZQ**, **12345** - Administrator Account
 
 **ADMIN**, **123456** - Administrator Account
 
 **SWE1809388**, **123** - Student Account
 
 **SWE1809375**, **123** - Student Account
 
 **SWE1809649**, **123** - Student Account
