<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['reference']) && isset($_POST['email']) && isset($_POST['password'])) {
 
    // receiving the post params
    $reference = $_POST['reference'];
    $email = $_POST['email'];
    $password = $_POST['password'];
 
    // check if user is already existed with the same email
    if ($db->isUserExisted($email)) {
        // user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $email;
        echo json_encode($response);
		
		
		
    } else {
		
		if ($db->isReferenceDeplucate($reference)) {
			// user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "Error in reffernce device Contact Citec To reslove this problem " . $reference;
        echo json_encode($response);
		

        }	else {
       // create a new user
        $user = $db->storeUser($reference, $email, $password);
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["uid"] = $user["unique_id"];
            $response["user"]["reference"] = $user["reference"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }

		
		}		
		
		
		
		
        
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (reference, email or password) is missing!";
    echo json_encode($response);
}
?>