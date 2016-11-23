<?php
$comment=$_POST['comment'];    
$post_id=$_POST['post_id'];    
$user_id=$_POST['user_id'];    
$date=$_POST['date'];

$con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
         
         $query="INSERT INTO comments(comment,date,post_id,user_id) VALUES 
                                 ('$comment','$date','$post_id','$user_id')";
            if( mysqli_query($con,$query))
                {
                      echo"true";
                }
        else {
         echo"false";
        }
 ?>
			