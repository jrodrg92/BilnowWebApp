const ctrlPetWin= require('./ctrlAddPetWin');
const ctrlPetInfoWin=require('./ctrlpetInfoWin');
const ctrlStore=require('./ctrlStore');


module.exports.showUser = function(res, user, petBD){

    console.log(user.id_Usuario);

    setOwner(user);
    petBD.findAll({model: this.petBD, where:{id_Duenio:user.id_Usuario}}).then(pets=>{
        setPets(pets);
        res.render('user', {pets,nom_Usuario:user.nom_Usuario,
                                              ap_Usuario:user.ap_Usuario, 
                                              tlf_Usuario:user.tlf_Usuario, 
                                             dir_Usuario:user.dir_Usuario, 
                                             email_Usuario:user.email_Usuario});

    });

};

module.exports.showMarket = function(req,res, prodBD){

    var buyer=getOwner();
    ctrlStore.showMarketWin(req,res,buyer, prodBD);
};

module.exports.showaddPetWin = function(req,res, petBD){

    var own=getOwner();
    ctrlPetWin.showAddPetWin(res,own,petBD);
 
};

module.exports.showPetInfo= function(req,res, petBD, dateBD){

    var own=getOwner();
    var petSelected = getPets(req.query.id);
    ctrlPetInfoWin.showPetInfoWin(req, res, petSelected ,own, petBD, dateBD);

};

module.exports.setPets = function(pets){

    this.pets=pets;

}

function setPets(pets){

    this.pets=pets;

}

function getPets(i){

    return this.pets[i];

}

module.exports.setOwner = function(owner){

    this.owner=owner;

}

function setOwner(owner){

    this.owner=owner;

}

function getOwner(){

    return this.owner;

}


