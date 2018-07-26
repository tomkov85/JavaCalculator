<?php
	
	require_once 'MyBlogView.php';
	
	$postSlug = $_GET["title"];
	$row = $qo->setContent("SELECT * FROM myblog.posts WHERE slug = '$postSlug'");
	?>
	<main style = 'float:left; width :80%; position: absolute; top: 100px'>
	<div>
	<table><tbody>
	<tr><td style = 'width: 500px;'><h2><?php echo $row->title?></h2></td><td><?php echo $row->postDate?></td></tr>
	<tr><td><?php echo $row->postText?></td></tr>
	<tr><td><b>Tags:</b><a href ='MyBlogHome.php?tag=<?php echo $row->tags?>'><?php echo $row->tags?></a></td></tr>
	</tbody></table>
	<br>
	<h3>Comments</h3>
	</div>
	
	<?php 
	if(empty($_SESSION['username'])) {
		echo "please <a href = 'MyBlogLogIn.php?source=$postSlug'>log in</a> for send a comment";
	} else {
		$name = $_SESSION['username'];
	?>
		<form method = POST action = ''>
		<a><?php echo $name ?> logged in</a><br>
		<br>
		<textarea name='commentArea' style = 'width:40%; height:100px;'></textarea><br>
		<input type='submit' name='commentAreaSubmit' value = 'send'>
		</form>
		<br>
	<?php
	}
	
	if(isset($_POST['commentAreaSubmit'])) {
		$text = $_POST['commentArea'];
		if(!empty($text)) {
		$qo->getSingleData("INSERT INTO myblog.comments VALUES (null, '$name', now(), '$text', '$row->id')");
		} else {
		echo "<a style = 'position: absolute; bottom: 20%'>Please fill all the fields</a>";
		}
	}
	
	$commentTable = $qo->setContentTable("SELECT * FROM myblog.comments WHERE postID = '$row->id' ORDER BY commentDate");
	
	foreach ($commentTable as $commentRow) {
	?>
	<div>
	<table><tbody>
	<tr style = 'background-color: grey'><td style = 'width: 500px;'><b><?php echo $commentRow->nickname?></b></td><td><?php echo $commentRow->commentDate?></td></tr>
	<tr><td><?php echo $commentRow->commentText?></td></tr>		
	</table></tbody>
	<br>
	</div>
	<?php
	}
	?>