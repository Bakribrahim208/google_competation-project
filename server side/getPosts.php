<?php
      
               $con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
          $query="select *from posts;";

$result=  mysqli_query($con,$query);
$response=  array();
while($row=  mysqli_fetch_array($result))

	{
    array_push($response, array(
        "id"=>$row[0],
        "post"=>$row[1],
        "photo"=>$row[2],
        "date"=>$row[3],
        "user_id"=>$row[4]));
	}

echo json_encode(array("posts"=>$response));
        
	
   
?>