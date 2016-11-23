<?php
$post=$_POST['post'];    
$photo=$_POST['photo'];    
$date=$_POST['date'];
$user_id=$_POST['user_id'];  
  
$con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
         
         $query="INSERT INTO posts(post,photo,date,user_id) VALUES 
                                 ('$post','$photo','$date','$user_id')";
            if( mysqli_query($con,$query))
                {
                      echo"true";
                }
        else {
         echo"false";
        }
 ?>
			