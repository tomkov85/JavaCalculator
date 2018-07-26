<?php
	class ServerConnection {
		private $dns = "mysql:host = 127.0.0.1; dbname = myblog";
		private $user = "root";
		private $password = "";
		private $connection;
		
		public function __construct() {
		try {
			$this->connection = new PDO ($this->dns, $this->user, $this->password);
		} catch (PDOException $e) {
			$e->getMessage();
		}			
	} 

	public function setContentTable($sql) {
		
    return $this->connection->query($sql)->fetchAll(PDO::FETCH_OBJ);
}
	public function setContentTableNum($sql) {
		
    return $this->connection->query($sql)->fetchAll(PDO::FETCH_NUM);
}
	public function setContent($sql) {
		
    return $this->connection->query($sql)->fetch(PDO::FETCH_OBJ);
}

	
	public function getSingleData($sql) {
		$result = $this->connection->query($sql)->fetch(PDO::FETCH_NUM);		
		return $result[0];    
}

	public function getTable($index, $table, $column) {
		$cc;
		if ($index - 1 < count($table)) {
			$cc = ($table[$index -1])[$column];
		} else {
			$cc = 0;
		}
		return $cc;
	}
}

?>