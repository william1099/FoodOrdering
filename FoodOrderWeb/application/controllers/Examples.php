<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Examples extends CI_Controller {

	public function __construct()
	{
		parent::__construct();

		$this->load->database();
		$this->load->helper('url');

		$this->load->library('grocery_CRUD');
	}

	public function _example_output($output = null)
	{
		$this->load->view('example.php',(array)$output);
	}

	public function offices()
	{
		$output = $this->grocery_crud->render();

		$this->_example_output($output);
	}

	public function index()
	{
		$this->_example_output((object)array('output' => '' , 'js_files' => array() , 'css_files' => array()));
	}

	public function customer()
	{
		try{
			$crud = new grocery_CRUD();

			$crud->set_theme('datatables');
			$crud->set_table('tbl_customer');
			$crud->set_subject('customer');
			$crud->required_fields('Nama');
			$crud->required_fields('Alamat');
			$crud->required_fields('No_Telepon');
			$crud->columns('ID_Customer','Nama','Jenis_Kelamin','Alamat','No_Telepon');

			$output = $crud->render();

			$this->_example_output($output);

		}catch(Exception $e){
			show_error($e->getMessage().' --- '.$e->getTraceAsString());
		}
	}
	
	public function makanan()
	{
		try{
			$crud = new grocery_CRUD();

			$crud->set_theme('datatables');
			$crud->set_table('tbl_makanan');
			$crud->set_subject('makanan');
			$crud->required_fields('Nama');
			$crud->required_fields('Harga');
			$crud->required_fields('Jenis');
			$crud->columns('ID_Makanan','Nama','Bahan_Pokok','Deskripsi','Harga','Gambar','Jenis');

			$output = $crud->render();

			$this->_example_output($output);

		}catch(Exception $e){
			show_error($e->getMessage().' --- '.$e->getTraceAsString());
		}
	}
	
	public function transaksi()
	{
		try{
			$crud = new grocery_CRUD();

			$crud->set_theme('datatables');
			$crud->set_table('tbl_transaksi');
			$crud->set_subject('transaksi');
			$crud->columns('ID_Transaksi','ID_Customer','ID_Makanan','Deskripsi','Jumlah_Pemesanan','Total_Harga','Tanggal_Pemesanan');

			$output = $crud->render();

			$this->_example_output($output);

		}catch(Exception $e){
			show_error($e->getMessage().' --- '.$e->getTraceAsString());
		}
	}
	
}
