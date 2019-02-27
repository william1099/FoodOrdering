<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require "../vendor/autoload.php";
require "db.php";

$app = new \Slim\App;

$app->get("/food/", function (Request $request,Response $response) {
	$sql = "SELECT ID_Makanan,Nama,Gambar,Harga FROM tbl_makanan WHERE Jenis='Makanan'";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->query($sql);
		$foods = $stmt->fetchAll(PDO::FETCH_OBJ);
		return $response->withStatus(200)->withHeader('Content-Type','application/json')->write(json_encode($foods, JSON_PRETTY_PRINT));

	} catch (PDOException $e) {
		echo $e->getMessage();
	}
	
});

$app->get("/paket/", function (Request $request,Response $response) {
	$sql = "SELECT ID_Makanan,Nama,Gambar,Harga FROM tbl_makanan WHERE Jenis='Paket'";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->query($sql);
		$foods = $stmt->fetchAll(PDO::FETCH_OBJ);
		return $response->withStatus(200)->withHeader('Content-Type','application/json')->write(json_encode($foods, JSON_PRETTY_PRINT));

	} catch (PDOException $e) {
		echo $e->getMessage();
	}
	
});


$app->get("/food/{id}", function (Request $request,Response $response) {
	$id = $request->getAttribute("id");
	$sql = "SELECT * FROM tbl_makanan WHERE id_makanan = '$id'";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->query($sql);
		$foods = $stmt->fetchAll(PDO::FETCH_OBJ);
		return $response->withStatus(200)->withHeader('Content-Type','application/json')->write(json_encode($foods, JSON_PRETTY_PRINT));

	} catch (PDOException $e) {
		echo $e->getMessage();
	}
	
});


$app->post("/food/add", function (Request $request,Response $response) {
	$Name = $request->getParam("Nama");
	$Bahan_Pokok = $request->getParam("Bahan_Pokok");
	$Deskripsi = $request->getParam("Deskripsi");
	$Harga = $request->getParam("Harga");
	$Gambar = $request->getParam("Gambar");

	$sql = "INSERT INTO tbl_makanan(Nama, Bahan_Pokok, Deskripsi, Harga, Gambar) VALUES(:Name, :Bahan_Pokok, :Deskripsi, :Harga, :Gambar)";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->prepare($sql);
		$stmt->bindParam(":Name", $Name);
		$stmt->bindParam(":Deskripsi", $Deskripsi);
		$stmt->bindParam(":Harga", $Harga);
		$stmt->bindParam(":Gambar", $Gambar);
		$stmt->bindParam(":Bahan_Pokok", $Bahan_Pokok);
		$stmt->execute();

		echo "Insert Sukses";

	} catch (PDOException $e) {
		echo "Insert Gagal";
		echo $e->getMessage();
	}
	
});

$app->post("/order/add", function (Request $request,Response $response) {
	$ID_Makanan = intval($request->getParam("ID_Makanan"));
	$ID_Customer = intval($request->getParam("ID_Customer"));
	$Deskripsi = $request->getParam("Deskripsi");
	$Jumlah_Pemesanan = $request->getParam("Jumlah_Pemesanan");
	$Harga = $request->getParam("Total_Harga");
	$Tanggal_Pemesanan = $request->getParam("Tanggal_Pemesanan");

	$sql = "INSERT INTO tbl_transaksi(ID_Makanan, ID_Customer, Deskripsi, Jumlah_Pemesanan, Total_Harga, Tanggal_Pemesanan) VALUES(:ID_Makanan, :ID_Customer, :Deskripsi, :Jumlah_Pemesanan, :Harga, :Tanggal_Pemesanan)";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->prepare($sql);
		$stmt->bindParam(":ID_Makanan", $ID_Makanan);
		$stmt->bindParam(":ID_Customer", $ID_Customer);
		$stmt->bindParam(":Deskripsi", $Deskripsi);
		$stmt->bindParam(":Harga", $Harga);
		$stmt->bindParam(":Jumlah_Pemesanan", $Jumlah_Pemesanan);
		$stmt->bindParam(":Tanggal_Pemesanan", $Tanggal_Pemesanan);
		$stmt->execute();

		$code = (object) array("insert", "sukses");

		return $response->withStatus(200)->withHeader('Content-Type','application/json')->write(json_encode($code, JSON_PRETTY_PRINT));


	} catch (PDOException $e) {
		$code = (object) array("insert", "gagal");
				return $response->withStatus(200)->withHeader('Content-Type','application/json')->write(json_encode($code, JSON_PRETTY_PRINT));
		echo $e->getMessage();
	}
	
});

$app->put("/food/update/{id}", function (Request $request,Response $response) {
	$id = $request->getAttribute("id");
	$Name = $request->getParam("Nama");
	$Bahan_Pokok = $request->getParam("Bahan_Pokok");
	$Deskripsi = $request->getParam("Deskripsi");
	$Harga = $request->getParam("Harga");
	$Gambar = $request->getParam("Gambar");

	$sql = "UPDATE tbl_makanan SET Nama = :Name,
								   Bahan_Pokok = :Bahan_Pokok, 
								   Deskripsi = :Deskripsi,
								   Harga =  :Harga,
								   Gambar = :Gambar WHERE id_makanan = $id";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->prepare($sql);
		$stmt->bindParam(":Name", $Name);
		$stmt->bindParam(":Deskripsi", $Deskripsi);
		$stmt->bindParam(":Harga", $Harga);
		$stmt->bindParam(":Gambar", $Gambar);
		$stmt->bindParam(":Bahan_Pokok", $Bahan_Pokok);
		$stmt->execute();

		echo "Update Sukses";

	} catch (PDOException $e) {
		echo "Update Gagal";
		echo $e->getMessage();
	}
	
});

$app->delete("/food/delete/{id}", function (Request $request,Response $response) {
	$id = $request->getAttribute("id");
	$sql = "DELETE FROM tbl_makanan WHERE id_makanan = '$id'";

	try {
		
		$db = new db();
		$db = $db->connect();
		$stmt = $db->query($sql);
		
		echo "Delete Suskes";

	} catch (PDOException $e) {
		echo "Delete Gagal";
		echo $e->getMessage();
	}
	
});


$app->run();
?>