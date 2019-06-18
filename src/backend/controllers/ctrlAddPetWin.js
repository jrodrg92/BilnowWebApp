var ctrlUser = require('./ctrlUser');

module.exports.showAddPetWin = function(res, user, petBD){
    setOwner(user);
    res.render('addPet');

};

module.exports.showmodPet = function(req, res, pet){

    res.render('modPet', {id_Mascota:pet.dataValues.id_Mascota,
                        nombre_Mascota:pet.dataValues.nombre_Mascota,
                        esp_Mascota:pet.dataValues.esp_Mascota,
                        raza_Mascota:pet.dataValues.raza_Mascota,
                        capa_Mascota:pet.dataValues.capa_Mascota,
                        fecha_Nacimiento:pet.dataValues.fecha_Nacimiento})

};

module.exports.modPet= function(req,res,petBD){

    var pet=getPet();
    var nom, esp, raz, capa;
    if(req.body.nombre_Mascota==null){
        nom=pet.nombre_Mascota;
    }
    else{
        nom=req.body.nombre_Mascota;
    }
    if(req.body.esp_Mascota==null){
        esp=pet.esp_Mascota;
    }
    else{
        esp=req.body.esp_Mascota;
    }
    if(req.body.raza_Mascota==null){
        raz=pet.raza_Mascota;
    }
    else{
        raz=req.body.raza_Mascota;
    }
    if(req.body.capa_Mascota==null){
        capa=pet.capa_Mascota;
    }
    else{
        capa=req.body.capa_Mascota;
    }

    petBD.update({nombre_Mascota:nom,
        esp_Mascota:esp,
        raza_Mascota:raz,
        capa_Mascota:capa
        },{where:{id_Mascota:pet.id_Mascota}}).then(
        pet=>{
            
        }
        );

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


function setOwner(owner){

    this.owner=owner;

}

function getOwner(){

    return this.owner;

}
function setPet(pet){

    this.pet=pet;

}

function getPet(){

    return this.pet;

}


