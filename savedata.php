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


$volume = 100;

//$quantity = $_GET['quantity'];

$quantity = 40;

$pourcentage = ($quantity*100)/100;

$sqlOne = "SELECT ID FROM data_citec ";

$resultOne = mysqli_query($conn,$sqlOne);

if(mysqli_num_rows($resultOne) > 0){

    //if found it is the first row
	
	
	$sqlThree = "SELECT quantite FROM data_citec ORDER BY ID DESC LIMIT 1 ";
	
	$resultThree = mysqli_query($conn,$sqlThree);
	
	if ($resultThree) {
		
		$row=mysqli_fetch_assoc($resultThree);
	  
	    $oldQuantity = (int)($row['quantite']);
	    //echo $oldQuantity;
	
	   
		
		if($oldQuantity>=$quantity){
			
			//echo "une vrai consommation";
			
			$newConsommation =  $oldQuantity-$quantity;
			$sqlFour = "INSERT INTO data_citec  (quantite, pourcentage, consommation) VALUES ($quantity, $pourcentage, $newConsommation) ";
			$resultFour = mysqli_query($conn,$sqlFour);
			echo "record created successfully";
			
			
		}else{
			//echo "La citerne a ete rempli";
			$newConsommation =  $oldQuantity-$quantity;
			$sqlFour = "INSERT INTO data_citec  (quantite, pourcentage, consommation) VALUES ($quantity, $pourcentage, '0') ";
			$resultFour = mysqli_query($conn,$sqlFour);
			echo "record created successfully";
			
		}
		
	   
       
     } else {
        
		echo "Error: " . $sql . "<br>" . $conn->error;
    }
	
	
	

}else{
   // if existe row(s)
    echo "first row";
	$sqlTow = "INSERT INTO data_citec  (quantite, pourcentage, consommation) VALUES ($quantity, $pourcentage, '0') ";
	$resultTow = mysqli_query($conn,$sqlTow);
	
	if ($resultTow) {
       echo "First record created successfully";
     } else {
       echo "Error: " . $sql . "<br>" . $conn->error;
    }

}






$conn->close();
?>