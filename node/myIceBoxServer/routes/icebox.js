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
	// http://localhost:3000/icebox/recipe?item="사과"
	var item = req.query.item;
	console.log(item)
	// if (item == "사과") { ....
});
module.exports = router;
