<?php

$servername = "localhost";
$username = "root";
$password = "Aqwzsxedc001";
$dbname = "moueihdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$current_year = date("Y");
$num_month = 12;

$json_array = array();
for ($i = 0; $i<$num_month;$i++){
	
	$sql = "SELECT SUM(consommation) FROM data_citec where month(timestamp)= $i and year(timestamp)=$current_year";
    $result = mysqli_query($conn,$sql);
	while ($row = mysqli_fetch_assoc($result))
          {
	
	         if ($row['SUM(consommation)']!=null){
				$row['month']=$i;
				$json_array [] = $row;
				 
			 }
			 

	
       }
	   
	  
}

    echo json_encode($json_array);
	
	
	



	
	
$conn->close();



?>