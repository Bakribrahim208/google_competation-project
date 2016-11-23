<?php
class getSaver{
      function retreve($x,$y){
                       $x1=$x+50;
                       $x2=$x-50;
                        $y1=$y+50;
                       $y2=$y-50;
               $con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
$query1="SELECT  `user_id` ,  `token` 
FROM  `users` 
WHERE location_x <".$x1."
AND location_x >".$x2.";";

$query2="SELECT  `user_id` ,  `token` 
FROM  `users` 
WHERE location_x <".$y1."
AND location_x >".$y2.";";

$result=  mysqli_query($con,$query1);
$response=  array();
while($row= mysqli_fetch_array($result))

	{
    array_push($response, array(
        "user_id"=>$row[0],
        "token"=>$row[1]));
	}


$result=  mysqli_query($con,$query2);

while($row= mysqli_fetch_array($result))

	{
    array_push($response, array(
        "user_id"=>$row[0],
        "token"=>$row[1]));
	}
return $response;
 }}
?>