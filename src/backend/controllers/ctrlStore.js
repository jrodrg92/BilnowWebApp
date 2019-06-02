const ctrlCarWin=require('./ctrlCarrito');

module.exports.showMarketWin = function(req,res,buyer, prodBD){

    setBuyer(buyer);
    setProductsTobuy();
    prodBD.findAll({model:this.prod}).then(prods => { 

        setProds(prods);
        res.render('store', {prods});
    }).catch(error => {
        console.log(error);
    });

};

module.exports.showCarritoWin = function(req,res){

    var buyer=getBuyer();
    var prodToBuy = getProductsTobuy();
    ctrlCarWin.showCarritotWin(req,res,buyer,prodToBuy);

};

module.exports.addProdLista = function(req,req,pos){

    var prod=getProd(pos);
    addNewProductsTobuy(prod);

};

function getProductsTobuy(){

    return this.prodsToBuy;

}

function setProductsTobuy(){

    this.prodsToBuy = [];

}

function addNewProductsTobuy(product){

    var i=0;

    while(this.prodsToBuy[i]!=null){
        i++;
    }

    this.prodsToBuy[i]=product;

}

function setBuyer(buyer){

    this.buyer=buyer;

}

function getBuyer(){

    return this.buyer;

}

function setProds(prods){

    this.prods=prods;

}

function getProd(i){

    return this.prods[i];

}


