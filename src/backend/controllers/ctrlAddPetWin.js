var ctrlUser = require('./ctrlUser');


function setOwner(owner){

    this.owner=owner;

}

function getOwner(){

    return this.owner;

}


module.exports.showAddPetWin = function(res, user, petBD){
    setOwner(user);
    res.render('addPet');

};


module.exports.addPet = function(req, res, petBD){

    var own=getOwner();
    
    petBD.create({ id_Mascota:req.body.id_Mascota,
        nombre_Mascota:req.body.nombre_Mascota,
        esp_Mascota:req.body.esp_Mascota,
        raza_Mascota:req.body.raza_Mascota,
        capa_Mascota:req.body.capa_Mascota,
        fecha_Nacimiento:new Date(req.body.datepicker),
        sexo_Mascota:req.body.sexo_Mascota,
        id_Duenio:own.id_Usuario});

    ctrlUser.showUser(res,own,petBD);

};

