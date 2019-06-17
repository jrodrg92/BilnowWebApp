const ctrlUser= require('./ctrlUser');

module.exports.back = function(req,res,pet){

    const usuario = getBuyer();
    ctrlUser.showUser(res, usuario, pet);

};

module.exports.showreservasWin = function(req,res,buyer, reservesBD){

    setBuyer(buyer);
    reservesBD.findAll({id_Usuario:buyer.id_Usuario}).then(reservas => { 
        res.render('reservasWin', {reservas} );
    }).catch(error => {
        console.log(error);
    });

};

function setBuyer(buyer){

    this.buyer=buyer;

}

function getBuyer(){

    return this.buyer;

}
