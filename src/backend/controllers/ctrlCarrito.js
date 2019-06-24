const faker= require('faker');
const ctrlStore=require('./ctrlStore');

module.exports.back = function(req,res,prodBD){

    const usuario = getBuyer();
    this.products=NULL;
    ctrlStore.showMarketWin(req,res,usuario, prodBD);

};

module.exports.showCarritotWin = function(req,res,buyer,products){

    setProds(products);
    setUser(buyer);
    res.render('carrito', {products});
    
};

module.exports.borrarprod = function(req,res,pos){

    var products=getProds();
    
    products.splice(pos,1);

    res.render('carrito', {products});

};

module.exports.makeBuy = function(req,res,reserveDB,prodreservedDB,prodDB){

    let idreserve = faker.random.uuid();
    var products=getProds();
    var user=getUser();

    for(var i=0; i<products.length; i++){

        prodreservedDB.create({id_Reserva:faker.random.uuid(), 
                                id_Producto:products[i].id_Producto, 
                                productosreservadoIdProducto:products[i].id_Producto}).then();


    }

    reserveDB.create({id_Reserva:idreserve, id_Usuario:user.id_Usuario}).then();    


    ctrlStore.showMarketWin(req,res,user,prodDB);

};

function setUser(user){
    this.user=user;
}
function getUser(){
    return this.user;
}
function setProds(prods){
    this.prods=prods;
}
function getProds(){
    return this.prods;
}

