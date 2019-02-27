# Food Ordering

FoodOrdering is a system that makes food ordering process easier. It allows user to see the detail of the menu retrieved by 
Rest API and order the food through mobile device. Once the user orders the food, all of the user datas including user 
information, description of the order are sent to the server and then get populated in the web page. That way, admin can 
perfom crud operation on the order detail


<div style="float:left;" >
<br>
<img src="https://github.com/william1099/FoodOrdering/blob/master/images/img1.jpg" width="200px" height=350px/>
<img src="https://github.com/william1099/FoodOrdering/blob/master/images/img2.jpg" width="200px" height=350px/>
</div>

## Installation

### Create Database 

```
CREATE DATABASE foodorder;

CREATE TABLE `tbl_customer` (
  `ID_Customer` int(11) NOT NULL,
  `Nama` varchar(60) NOT NULL,
  `Alamat` varchar(70) NOT NULL,
  `Jenis_Kelamin` varchar(10) NOT NULL,
  `No_Telepon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_makanan` (
  `ID_Makanan` int(11) NOT NULL,
  `Nama` varchar(75) NOT NULL,
  `Bahan_Pokok` varchar(30) NOT NULL,
  `Deskripsi` text NOT NULL,
  `Harga` int(11) NOT NULL,
  `Gambar` varchar(200) NOT NULL,
  `Jenis` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_transaksi` (
  `ID_Transaksi` int(11) NOT NULL,
  `ID_Makanan` int(11) NOT NULL,
  `ID_Customer` int(11) NOT NULL,
  `Deskripsi` text NOT NULL,
  `Jumlah_Pemesanan` int(11) NOT NULL,
  `Total_Harga` varchar(255) NOT NULL,
  `Tanggal_Pemesanan` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

```

### Rest API Setting
Change <> according to your database setting
```
// FoodOrderRestAPI/public/db.php

  public $db_host = '<Your database host>';
	public $db_username = '<database username>';
	public $db_password = '';
	public $db_name = '<your database name';
  
```

### Web Setting
``` 
  // FoodOrderingWeb/application/config/database.php
  
  $db['default'] = array(
	'dsn'	=> '',
	'hostname' => '<Your database host>',
	'username' => '<database username>',
	'password' => '',
	'database' => '<your database name',
	'dbdriver' => 'mysqli',
 ```
 
 ### Android App Setting
 Change <> according to your server name. ex : test.com
 ```
 // FragmentA.java, FragmentB.java, Main3Activity.java, Main4Activity.java, Main5Activity.java
 public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        data = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "<your-server>/FoodOrderWeb/public/food/",
                new Response.Listener<String>() {
                ....
 ```
