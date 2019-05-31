const ctrlUser = require('../controllers/ctrlUser');
const faker= require('faker');
class CtrlCarWin{
    
    constructor(route, res, dir, user, reserve, prodreserved, products){

        this.dir=dir;
        this.user=user;
        this.res=res;
        this.products=products;
        this.reserve=reserve;
        this.prodreserved=prodreserved;

        this.res.render(dir + '/views/carrito', {products});


        route.post('/borrarProd',(req,res,next)=>{

            const prodsMod=[];

            this.products.splice(req.query.id,1);

            next(res.render(dir + '/views/carrito', {products}));

        });

        route.post('/makeBuy', (req,res,next)=>{

            let reserve = faker.random.uuid();
            console.log(reserve.length);


        });

    }

}

module.exports = CtrlCarWin;