package desk.mitienda.controller;

import desk.mitienda.dao.IvaDao;
import desk.mitienda.utils.JPAUtils;

public class IvaController {

    private IvaDao ivaDao;

    public IvaController() {
        ivaDao = new IvaDao(JPAUtils.getEntityManager());
    }


}
