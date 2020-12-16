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


$sql = "SELECT * FROM chart ";
$result = mysqli_query($conn,$sql);
$json_array = array();


while ($row = mysqli_fetch_assoc($result))
{
	$json_array [] = $row;

	
}
	echo json_encode($json_array);
	
$conn->close();


?>