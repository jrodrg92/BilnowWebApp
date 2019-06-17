const ctrlCarWin=require('./ctrlCarrito');
const ctrlUser= require('./ctrlUser');
const ctrlAddProd = require('./ctrlAddProd');
const ctrlReservesWin = require('./ctrlReservasWin');

module.exports.back = function(req,res,pet){

    const usuario = getBuyer();
    ctrlUser.showUser(res, usuario, pet);

};

module.exports.showMarketWin = function(req,res,buyer, prodBD){

    setBuyer(buyer);
    setProductsTobuy();
    prodBD.findAll({model:this.prod}).then(prods => { 

        setProds(prods);
        res.render('store', {prods,buyer});
    }).catch(error => {
        console.log(error);
    });

};

module.exports.showCarritoWin = function(req,res){

    var buyer=getBuyer();
    var prodToBuy = getProductsTobuy();
    ctrlCarWin.showCarritotWin(req,res,buyer,prodToBuy);

};

module.exports.showaddProd = function(req,res){

    var buyer=getBuyer();
    ctrlAddProd.showaddProd(res,buyer);
   

};

module.exports.comprasUser = function(req,res,reservesBD){

    var buyer=getBuyer();
    ctrlReservesWin.showreservasWin(req,res,buyer,reservesBD);
   

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


