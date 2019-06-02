var ctrlUser = require('./ctrlUser');
const faker= require('faker');

module.exports.showPetInfoWin= function(req, res, pet ,own, petBD, dateBD){

    setPet(pet);
    setOwner(own)

    dateBD.findAll({model:this.dateBD, where:{id_Mscota:pet.dataValues.id_Mascota}}).then(dates => {

        setDates(dates);

        res.render('petWin', {dates, id_Mascota:pet.dataValues.id_Mascota,
                                           nombre_Mascota:pet.dataValues.nombre_Mascota,
                                           esp_Mascota:pet.dataValues.esp_Mascota,
                                           raza_Mascota:pet.dataValues.raza_Mascota,
                                           capa_Mascota:pet.dataValues.capa_Mascota,
                                           fecha_Nacimiento:pet.dataValues.fecha_Nacimiento});
      });       

};

module.exports.addCita= function(req, res , dateBD){

    var pet=getPet();
    dateBD.create({ID_Cita:faker.random.uuid(),
                 fecha_Cita:new Date(req.body.datepicker),
                 id_Mscota:pet.dataValues.id_Mascota});

    //AÑADIR CITA
    dateBD.findAll({model:this.dateBD, where:{id_Mscota:pet.dataValues.id_Mascota}}).then(dates => {

        setDates(dates);

        res.render('petWin', {dates, id_Mascota:pet.dataValues.id_Mascota,
                                           nombre_Mascota:pet.dataValues.nombre_Mascota,
                                           esp_Mascota:pet.dataValues.esp_Mascota,
                                           raza_Mascota:pet.dataValues.raza_Mascota,
                                           capa_Mascota:pet.dataValues.capa_Mascota,
                                           fecha_Nacimiento:pet.dataValues.fecha_Nacimiento});
      });       


};

module.exports.deletePet= function(req, res , petBD, dateBD){

    var pet=getPet();
    var own=getOwner();
    petBD.destroy({where:{id_Mascota:pet.dataValues.id_Mascota}});
    ctrlUser.showUser(res,own,petBD);

};

module.exports.borraCita= function(req, res ,petBD, dateBD){

    var pet=getPet();
    var own=getOwner();
    var CitaSelect=getDAtes(req.query.id);
    dateBD.destroy({where:{ID_Cita:CitaSelect.ID_Cita}});
  
    dateBD.findAll({model:this.date, where:{id_Mscota:pet.dataValues.id_Mascota}}).then(dates => {

        setDates(dates);

        res.render('petWin', {dates, id_Mascota:pet.dataValues.id_Mascota,
                                           nombre_Mascota:pet.dataValues.nombre_Mascota,
                                           esp_Mascota:pet.dataValues.esp_Mascota,
                                           raza_Mascota:pet.dataValues.raza_Mascota,
                                           capa_Mascota:pet.dataValues.capa_Mascota,
                                           fecha_Nacimiento:pet.dataValues.fecha_Nacimiento});
      });       

};

function setPet(pet){

    this.pet=pet;

}

function getPet(){

    return this.pet;

}

function setDates(dates){

    this.dates=dates;

}

function getDAtes(i){

    return this.dates[i];

}

function setOwner(owner){

    this.owner=owner;

}

function getOwner(){

    return this.owner;

}
