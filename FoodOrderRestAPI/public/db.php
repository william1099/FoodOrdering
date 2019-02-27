<?php
class db{

	public $db_host = '<Your database host>';
	public $db_username = '<database username>';
	public $db_password = '';
	public $db_name = '<your database username';
	public $opt = [
    	PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    	PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    	PDO::ATTR_EMULATE_PREPARES   => false,
	];

	function connect() {
		$dsn = "mysql:host=$this->db_host;dbname=$this->db_name";
		$PDO = new PDO($dsn, $this->db_username, $this->db_password, $this->opt);
		return $PDO;
	}
	
}



?>