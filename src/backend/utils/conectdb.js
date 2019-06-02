const Sequelize = require('sequelize');

module.exports = {

  getConection: function(){

    const sequelize =  new Sequelize('bilnow_db', 'root', '', {

      host: 'localhost',
      dialect: 'mysql',
      pool: {
          max: 5,
          min: 0,
          idle: 10000
         },
         });
                
         sequelize.authenticate()
           .then(() => {
                console.log('Connection has been established successfully.');
            })
            .catch(err => {
               console.error('Unable to connect to the database:', err);
          });
      

          return sequelize;

  }
  
};