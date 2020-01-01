# Inventario Grano De Oro

The system will be based on changing the manual process that the company uses to make its inventories to software that 
facilitates this work. The software will run on cell phones with Android operating system, because the company will provide
these devices to perform tests; Being mobile devices, workers can carry out the process easily and quickly. The main function
of the software is to carry out through the camera a reading of the barcode of the product that will be inventoried, a database
will be used which will be fed with the information that the company will provide us, in which the products with their products
will be specified. attributes You will also have the product search options by manually entering your name or your code, since
in the company there are products that handle internal codes and the label with the bar code is not visible.


## Login Screen
<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/5t2sSXe.jpg">
</p>

Two users will be used, one of them will be the administrator who will need a password to access the system, which will only 
allow him to generate the final report, and a standard user which will be used in general and he will not have the option to 
generate final report. The purpose of the standard user is to scan the products and enter the quantities that will be stored 
in the database. Being different standard users working at the same time, the amounts will be added to the database at the 
time of saving, as there may be duplicates of codes.

## Standard user main screen
<p align="center">
  <img width="300" height="220" src="https://i.imgur.com/Ouam7LO.png">
</p>

Standard users can modify the information before saving it to the database, since errors may arise when entering quantities 
and the possibility of removing the product from their list, in the event that the user has selected an item by mistake .

**search by scanner**
<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/hwiewVh.jpg">
    <img width="300" height="500" src="https://i.imgur.com/0nu6Tux.jpg">
</p>

**search by code**
<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/ip08aY6.jpg">
</p>

**Search by name**
<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/5qdOBqe.jpg">
</p>

**Update inventory**
<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/PaJPhe2.jpg">
</p>

Once the standard users have finished saving their information, the administrator user will have to generate the report by 
clicking on a button. The report must be in TXT format and with the nomenclature “CODE, QUANTITY”. The purpose of the software
is to arrive at that report because through this the stock of your point of sale system called MR is fed. STORE.

## Main administrator screen

<p align="center">
  <img width="300" height="500" src="https://i.imgur.com/mwiXQnz.jpg">
  <img width="300" height="120" src="https://i.imgur.com/4UzTrNS.png">
</p>

The system will have to be able to set to 0 all the quantities that the database of the previous month has, so that the same 
process can be carried out in a new month. To avoid external modifications, everything will be done in a local environment on 
the same network as there is sensitive information for the company.
