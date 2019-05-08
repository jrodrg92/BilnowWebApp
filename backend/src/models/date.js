const Sequelize = require('sequelize')

module.exports = {

    getDate: function(sequelize){

        const Date=sequelize.define("datos",{
            ID_Cita:{type:Sequelize.INTEGER, primaryKey:true},
            fecha_Cita: Sequelize.DATE,
            id_Mascota: Sequelize.STRING
        });

        return Date;

    }

};