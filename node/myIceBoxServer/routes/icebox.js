var express = require('express');
var router = express.Router();

/* GET json result. */
//http://localhost:3000/icebox
router.get('/', function(req, res) {
	res.setHeader('Content-Type', 'application/json');
	var sampleJson = { icebox : [
					{id : 10000, desc : "my ice box", material : "apple egg juice"},
					{id : 10003, desc : "your ice box", material : "cake chicken"}]}
	res.end(JSON.stringify(sampleJson));
});

router.get('/recipe', function(req, res) {
	// http://localhost:3000/icebox/recipe?item=사과
	var item = req.query.item;
	console.log(item)
	
	res.setHeader('Content-Type', 'application/json');
	if (item == "계란") {
		var recipeJson = { recipelist : [
					{title : "계란말이", items : "계란 파 치즈", recipe : "#1 계란을 그릇에 푼다. #2 파를 잘게 썰어서 넣는다. #3 치즈를 넣고 섞는다. #4 후라이팬에 잘 익힌다."}]}	res.end(JSON.stringify(recipeJson));
		res.end(JSON.stringify(recipeJson));
	}
});
module.exports = router;
