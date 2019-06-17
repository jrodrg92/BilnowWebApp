const ctrlStore = require('./ctrlStore');

module.exports.showaddProd = function(res, buyer){

    setBuyer(buyer);
    res.render('addProd');

};

module.exports.addprod = function(req,res,prod){

    var buyer=getBuyer();
    prod.create({
        id_Producto:req.body.id_Producto,
        nom_Producto:req.body.nom_Producto,
        foto_Producto:req.body.foto_Producto,
        fabricante_Pro:req.body.fabricante_Pro,
        price_Producto:req.body.price_Producto,
    }).then(()=>{

        ctrlStore.showMarketWin(req,res,buyer,prod);

    });
    
};

function setBuyer(buyer){

    this.buyer=buyer;

}

function getBuyer(){

    return this.buyer;

}
