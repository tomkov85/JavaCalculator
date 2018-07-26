<!DOCTYPE html>
<html>
<head>
<?php require_once 'MyBlogConnection.php' ;
	session_start();
	$username = "";
	function headerString() {
	if(!empty($_SESSION["username"])) {
		$username = $_SESSION["username"];
		echo "<a href='MyBlogLogOut.php'>$username</a>";
	} else {
		$username = "Log In";
		echo "<a href='MyBlogLogIn.php?source=home'>$username</a>";
	}
	}
?>
<meta charset="UTF-8">
<title>MyBlog</title>
<link rel = "stylesheet" type="text/css" href = "MyBlog.css">
</head>
<body>
<header>
	<h1><a id = "title" href = "MyBlogHome.php" >Welcome to my World</a></h1>
	<a><?php headerString(); ?></a>
</header>
<aside>
<div>
	<p>
		Hi, I'am Ben and this is my blog. I'm interested about many things, so i hope'll not bore here.
	</p>
</div>
<div>
	<h4>Last Comments:</h4>
<?php
	$qo = new ServerConnection();
	$qoResult = $qo->setContentTable("SELECT * FROM myblog.comments ORDER BY commentDate LIMIT 3");
	$postnames = $qo->setContentTableNum("SELECT title, slug FROM myblog.posts");
	
	
	foreach($qoResult as $row) {
		$postTitle = $qo->getTable($row->postId, $postnames, 0);
		$postSlug = $qo->getTable($row->postId, $postnames, 1);
?>
		<div id = "lastCommentsBox" style = 'overflow : hidden; max-width:90%; max-height:20%;'>
		<table><tbody>
		<tr><td><?php echo $row->commentText?></td></tr>
		<tr><td><?php echo $row->nickname,$row->commentDate ?></td></tr>
		<tr><td><a href = 'MyBlogPost.php?title=<?php echo $postSlug?>'><?php echo $postTitle ?></a></td></tr> 
		</table></tbody>
		<br>
		</div>
	<?php } ?>	
	
</div>
<div>
	<h4>Tags:</h4>
	<?php
		$tags = $qo->setContentTableNum("SELECT tags FROM myblog.posts GROUP BY tags ORDER BY tags");
		foreach ($tags as $tag) {
			echo "<a href ='MyBlogHome.php?tag=$tag[0]'>$tag[0]</a>" , " "; 
		}
	?>
</div>
</aside>
<footer>
	<a>@copyright Ben</a><br>
	<a>2018</a>
</footer>
</body>
</html>

