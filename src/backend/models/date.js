const Sequelize = require('sequelize')

module.exports = {

    getDate: function(sequelize, pet){

        const Date=sequelize.define("citas",{
            ID_Cita:{type:Sequelize.INTEGER, primaryKey:true},
            fecha_Cita: Sequelize.DATE,
            id_Mscota: Sequelize.STRING
        });

        pet.hasMany(Date);

        return Date;

    }

};