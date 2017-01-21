<?php

require 'BookServerDataModel.php';
$db = new BookDB();


if (ISSET($_POST["edit"]) || ISSET($_POST["add"])) {
	if (ISSET($_POST["isbn"])&&ISSET($_POST["title"])&&ISSET($_POST["author"])&&ISSET($_POST["year"])&&ISSET($_POST["publisher"])
	&&ISSET($_POST["genre"])&&ISSET($_POST["seller_name"])&&ISSET($_POST["price"])&&ISSET($_POST["buynow"])&&ISSET($_POST["quantity"])&&ISSET($_POST["condition"]))
	{
		$book = new Book();
		
		$book->isbn = $_POST["isbn"];
		$book->title = $_POST["title"];
		$book->author = $_POST["author"];
		$book->publisher = $_POST["publisher"];
		$book->genre = $_POST["genre"];
		$book->year = $_POST["year"];
		$book->seller_name = $_POST["seller_name"];
		$book->price = $_POST["price"];
		$book->buynow = $_POST["buynow"];
		$book->quantity = $_POST["quantity"];
		$book->book_condition = $_POST["condition"];
		
		if(ISSET($_POST["edit"]))
		{
			$db->edit($book);
		}
		
		else if(ISSET($_POST["add"]))
		{
			$db->add($book);
		}
	}
} elseif (ISSET($_POST["remove"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		
		/*
		if(ISSET($_POST["title"])&&""!=$_POST["title"])
		{
			$book->title = $_POST["title"];
		}		
		if(ISSET($_POST["author"])&&""!=$_POST["author"])
		{
			$book->author = $_POST["author"];
		}
		if(ISSET($_POST["publisher"])&&""!=$_POST["publisher"])
		{
			$book->publisher = $_POST["publisher"];
		}
		if(ISSET($_POST["genre"])&&""!=$_POST["genre"])
		{
			$book->genre = $_POST["genre"];
		}
		if(ISSET($_POST["price"])&&""!=$_POST["price"])
		{
			$book->price = $_POST["price"];
		}
		if(ISSET($_POST["quantity"])&&""!=$_POST["quantity"])
		{
			$book->quantity = $_POST["quantity"];
		}
		if(ISSET($_POST["condition"])&&""!=$_POST["condition"])
		{
			$book->condition = $_POST["condition"];
		}*/
		
		$db->remove($book);
		
	} else
			printf("false");

} elseif (ISSET($_POST["getHighestBid"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		$book->book_condition = $_POST["condition"];
		$db->getHighestBid($book);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_credits"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {
		$db->getCredits($_POST["display_name"]);
	} else
			printf("false");

} elseif (ISSET($_POST["get_top_users"])) {
		$db->getTopUsers();

} elseif (ISSET($_POST["get_top_books"])) {
		$db->getTopBooks();

} elseif (ISSET($_POST["get_new_users"])) {
		$db->getNewUsers();

} elseif (ISSET($_POST["add_credits"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]&&ISSET($_POST["credits"])&&""!=$_POST["credits"]) {
		$user = new User();
		$user->display_name = $_POST["display_name"];
		$user->credits = $_POST["credits"];
		$db->addCredits($user);
	} else
			printf("false");

} elseif (ISSET($_POST["accept_user"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {
		$db->acceptUser($_POST["display_name"]);
	} else
			printf("false");

} elseif (ISSET($_POST["decline_user"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {
		$db->declineUser($_POST["display_name"]);
	} else
			printf("false");

} elseif (ISSET($_POST["unsuspend_user"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {
		$db->removeUserComplaints($_POST["display_name"]);
	} else
			printf("false");

} elseif (ISSET($_POST["unsuspend_book"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		$db->removeSuspendedBook($book);
		
	} else
			printf("false");

} elseif (ISSET($_POST["post_bid"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["title"])&&""!=$_POST["title"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]
	&&ISSET($_POST["buyer_name"])&&""!=$_POST["buyer_name"]&&ISSET($_POST["bid_amount"])&&""!=$_POST["bid_amount"]&&ISSET($_POST["buy_now"])&&""!=$_POST["buy_now"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->title = $_POST["title"];
		$book->seller_name = $_POST["seller_name"];
		$book->book_condition = $_POST["condition"];
		$book->price = $_POST["bid_amount"];
		$db->postBid($book, $_POST["buyer_name"], $_POST["buy_now"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["add_review"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]
	&&ISSET($_POST["buyer_name"])&&""!=$_POST["buyer_name"]) {
		$review = new Review();
		$review->book_isbn = $_POST["isbn"];
		$review->seller_name = $_POST["seller_name"];
		$review->book_condition = $_POST["condition"];
		$review->buyer_name = $_POST["buyer_name"];
		if (ISSET($_POST["reviewed_by_buyer"]))
			$review->reviewed_by_buyer = $_POST["reviewed_by_buyer"];
		elseif (ISSET($_POST["reviewed_by_seller"]))
			$review->reviewed_by_seller = $_POST["reviewed_by_seller"];
		if (ISSET($_POST["book_stars"]))
			$review->book_stars = $_POST["book_stars"];
		if (ISSET($_POST["seller_stars"]))
			$review->seller_stars = $_POST["seller_stars"];
		if (ISSET($_POST["buyer_stars"]))
			$review->buyer_stars = $_POST["buyer_stars"];
		if (ISSET($_POST["book_comments"]))
			$review->book_comments = $_POST["book_comments"];
		if (ISSET($_POST["seller_comments"]))
			$review->seller_comments = $_POST["seller_comments"];
		if (ISSET($_POST["buyer_comments"]))
			$review->buyer_comments = $_POST["buyer_comments"];
		$db->addReview($review);
		
	} else
			printf("false");

} elseif (ISSET($_POST["cancel_bid"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]
	&&ISSET($_POST["buyer_name"])&&""!=$_POST["buyer_name"]&&ISSET($_POST["bid_amount"])&&""!=$_POST["bid_amount"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		$book->book_condition = $_POST["condition"];
		$book->price = $_POST["bid_amount"];
		$db->cancelBid($book, $_POST["buyer_name"]);
		
	} else
			printf("false");

}  elseif (ISSET($_POST["accept_bid"])) {
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]
	&&ISSET($_POST["buyer_name"])&&""!=$_POST["buyer_name"]&&ISSET($_POST["bid_amount"])&&""!=$_POST["bid_amount"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		$book->book_condition = $_POST["condition"];
		$book->price = $_POST["bid_amount"];
		$db->acceptBid($book, $_POST["buyer_name"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_bids"])) {
	if(ISSET($_POST["buysell"])&&""!=$_POST["buysell"]&&ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {

		$db->getBids($_POST["buysell"], $_POST["display_name"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_bid_history"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {

		$db->getBidHistory($_POST["display_name"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_browse_history"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]) {

		$db->getBrowseHistory($_POST["display_name"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_reviews"])) {
	if(ISSET($_POST["buysell"])&&""!=$_POST["buysell"]&&ISSET($_POST["display_name"])&&""!=$_POST["display_name"]&&ISSET($_POST["reviewed"])&&""!=$_POST["reviewed"]) {

		$db->getReviews($_POST["buysell"], $_POST["display_name"], $_POST["reviewed"]);
		
	} else
			printf("false");

} elseif (ISSET($_POST["get_one"])) {
	if(ISSET($_POST["display_name"])&&""!=$_POST["display_name"]&&ISSET($_POST["isbn"])&&""!=$_POST["isbn"]&&ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"]&&ISSET($_POST["condition"])&&""!=$_POST["condition"]) {
		$book = new Book();
		$book->isbn = $_POST["isbn"];
		$book->seller_name = $_POST["seller_name"];
		$book->book_condition = $_POST["condition"];
		$db->getBook($book, $_POST["display_name"]);
	}
	
} elseif (ISSET($_POST["search"])) {
	$constraint = new BookConstraint();
	
	if(ISSET($_POST["title"])&&""!=$_POST["title"])
	{
		$constraint->title = $_POST["title"];
	}
	
	if(ISSET($_POST["titleExact"])&&$_POST["titleExact"]=="true")
	{
		$constraint->titleExact = true;
	}
	
	if(ISSET($_POST["isbn"])&&""!=$_POST["isbn"])
	{
		$constraint->isbn = $_POST["isbn"];
	}
	
	if(ISSET($_POST["author"])&&""!=$_POST["author"])
	{
		$constraint->author = $_POST["author"];
	}
	
	if(ISSET($_POST["authorExact"])&&$_POST["authorExact"]=="true")
	{
		$constraint->authorExact = true;
	}
	
	if(ISSET($_POST["publisher"])&&""!=$_POST["publisher"])
	{
		$constraint->publisher = $_POST["publisher"];
	}
	
	if(ISSET($_POST["publisherExact"])&&$_POST["publisherExact"]=="true")
	{
		$constraint->publisherExact = true;
	}
	
	if(ISSET($_POST["genre"])&&""!=$_POST["genre"])
	{
		$constraint->genre = $_POST["genre"];
	}
	
	if(ISSET($_POST["genreExact"])&&$_POST["genreExact"]=="true")
	{
		$constraint->genreExact = true;
	}
	
	if(ISSET($_POST["seller_name"])&&""!=$_POST["seller_name"])
	{
		$constraint->seller_name = $_POST["seller_name"];
	}
	
	if(ISSET($_POST["sellerExact"])&&$_POST["sellerExact"]=="true")
	{
		$constraint->sellerExact = true;
	}
	
	if(ISSET($_POST["minPrice"])&&""!=$_POST["minPrice"])
	{
		$constraint->minPrice = $_POST["minPrice"];
	}
	
	if(ISSET($_POST["maxPrice"])&&""!=$_POST["maxPrice"])
	{
		$constraint->maxPrice = $_POST["maxPrice"];
	}
	
	if(ISSET($_POST["minYear"])&&""!=$_POST["minYear"])
	{
		$constraint->minYear = $_POST["minYear"];
	}
	
	if(ISSET($_POST["maxYear"])&&""!=$_POST["maxYear"])
	{
		$constraint->maxYear = $_POST["maxYear"];
	}
	
	$db->search($constraint);
	
} elseif (ISSET($_POST["register"])) {
	if (ISSET($_POST["login_name"]) && ISSET($_POST["display_name"])) {
		$user = new User();
		$user->login_name = $_POST["login_name"];
		$user->display_name = $_POST["display_name"];
		$db->register($user);
	} else {
		printf("3");
	}

} elseif (ISSET($_POST["login"])) {
	if (ISSET($_POST["login_name"]) && ISSET($_POST["password"])) {
		$user = new User();
		$user->login_name = $_POST["login_name"];
		$user->password_hash = $_POST["password"];
		$db->login($user);
	} else {
		printf("false");
	}
	
}

elseif (ISSET($_POST["login_admin"])) {
	if (ISSET($_POST["login_name"]) && ISSET($_POST["password"])) {
		$user = new User();
		$user->login_name = $_POST["login_name"];
		$user->password_hash = $_POST["password"];
		$db->login($user);
	} else {
		printf("false");
	}
	
}
	
	elseif (ISSET($_POST["changepass"])) {
	if (ISSET($_POST["display_name"]) && ISSET($_POST["current_password"]) && ISSET($_POST["new_password"])) {
		$c = new User();
		$n = new User();
		$c->display_name = $_POST["display_name"];
		$c->password_hash = $_POST["current_password"];
		$n->password_hash = $_POST["new_password"];
		$db->changepass($c, $n);
	} else {
		printf("false");
	}
	
}

?>
