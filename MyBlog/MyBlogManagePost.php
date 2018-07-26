<?php

	require_once 'MyBlogView.php';
	
	if($_GET["delete"] != null) {
		$delete = $_GET["delete"];
		$qo->setContent("DELETE FROM myblog.posts WHERE slug = '$delete'");
		header('location:MyBlogHome.php');
	}
	$title = $_GET["title"];
	?>
	<main style = 'float:left; width :80%; position: absolute; top: 100px' ><div>
	<form method='POST' action = ''><table style = 'width: 100%;'><tbody>
	<?php
	if($title == "0") {
		?>
		<tr><td><label><b>Title: </b></label><input type = 'text' name = 'postTitle'/></td></tr>
		<tr><td><textarea name='postArea' style = 'width:40%; height:100px;'></textarea></td></tr>
		<tr><td><label><b>Tags: </b></label><input type = 'text' name = 'postTag'/></td></tr>
	<?php
	} else {		
		$qoResult = $qo->setContent("SELECT * FROM myblog.posts WHERE slug = '$title'");
		?>
		<tr><td><label>Title:</label><input type = 'text' name = 'postTitle' value = '<?php echo $qoResult->title ?>'/></td></tr>
		<tr><td><textarea name='postArea' style = 'width:40%; height:100px;'><?php echo $qoResult->postText ?></textarea></td></tr>
		<tr><td><label>Tags:</label><input type = 'text' name = 'postTag' value = '<?php echo $qoResult->tags?>'/></td></tr>
	<?php
	}
		?>
		</tbody></table><br>
		<input type = 'submit' name = 'modify' value = 'send'/>
		<form>
	 </div></main>
	<?php
	if(isset($_POST["modify"])){
		$postTitle = $_POST["postTitle"];
		$postText = $_POST["postArea"];
		$postTag = $_POST["postTag"];
		$author = $_SESSION["username"];
		if (!empty($postTitle) & !empty($postText)) {
		$j = 0;
		$postSlug = "";
		for($i = 0; $i < strlen($postTitle); $i++) {
				if($postTitle[$i] != " ") {
					$postSlug[$j] = $postTitle[$i];
				} else {
					$postSlug[$j] = "_";
				}
				$j++;
		}
		
		if($title == "0") {
			$qo->setContent("INSERT INTO myblog.posts VALUES (null, '$postTitle', now(), '$postText', '$postTag','$postSlug','$author')");
		} else {
			$qo->setContent("UPDATE myblog.posts SET title = '$postTitle', postText = '$postText', tags = '$postTag' WHERE slug = '$title' ");
		}
		header('location:MyBlogHome.php');
		} else {
			echo "<a style = 'position: absolute; top: 50%'>Please fill all the fields</a>";
		}
	}
?>
	
		
		
	
	
	