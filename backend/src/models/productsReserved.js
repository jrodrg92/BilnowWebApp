const Sequelize = require('sequelize')

module.exports = {

    getProductRes: function(sequelize){

        const ProductRes=sequelize.define("productosreservados",{
           
            id_Reserva: {type: Sequelize.STRING, primaryKey:true},
            id_Producto: Sequelize.STRING

        });
        
        return ProductRes;

    }

};