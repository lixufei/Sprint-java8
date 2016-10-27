package test.functional.product;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.product.dao.ProductDao;
import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;
import com.thoughtworks.gaia.user.dao.UserDao;
import com.thoughtworks.gaia.user.entity.User;
import com.thoughtworks.gaia.user.model.UserModel;
import com.thoughtworks.gaia.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class OneToManyFunctionalTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void should_get_user_prod_with_user_id() throws Exception {
        UserModel userModel = userProdModel();
        userDao.save(userModel);
        Long userId = userModel.getId();

        User user = userService.getUser(userId);

        System.out.println("produ info:"+user.getProducts().get(0).getName());

        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getName()).isEqualTo("user");
        assertThat(user.getAge()).isEqualTo(18);
        assertThat(user.getProducts().size()).isEqualTo(2);
    }

    private ProductModel prodModel1 () {
        ProductModel productModela = new ProductModel();
        productModela.setName("product1");
        productModela.setTimeCreated(new Date());

        return productModela;
    }

    private ProductModel prodModel2 () {
        ProductModel productModelb = new ProductModel();
        productModelb.setName("product2");
        productModelb.setTimeCreated(new Date());

        return productModelb;
    }

    private ProductModel prodModel3 () {
        ProductModel productModelc = new ProductModel();
        productModelc.setName("product3");
        productModelc.setTimeCreated(new Date());

        return productModelc;
    }

    private UserModel userProdModel() {
        UserModel userModel = new UserModel();
        List<ProductModel> prods = new ArrayList<ProductModel>();
        prods.add(prodModel1());
        prods.add(prodModel2());

        userModel.setProducts(prods);
        userModel.setName("user");
        userModel.setAge(18);
        return userModel;
    }

    private UserModel updatedUserModel() {
        UserModel userModel = new UserModel();

        List<ProductModel> udpatedProds = new ArrayList<ProductModel>();
        udpatedProds.add(prodModel3());

        userModel.setProducts(udpatedProds);
        userModel.setName("name");
        userModel.setAge(16);

        return userModel;
    }

    @Test
    public void should_update_a_prod_in_user() throws Exception {

        UserModel userModel = userProdModel();
        userDao.save(userModel);

        User updatedUser = userService.updateUser(updatedUserModel());

        System.out.println("user di:"+updatedUser.getProducts().get(0).getTimeCreated());
        System.out.println("user di:"+updatedUser.getProducts().get(0).getName());

        assertThat(updatedUser.getName()).isEqualTo("name");
        assertThat(updatedUser.getAge()).isEqualTo(16);
        assertThat(updatedUser.getProducts().get(0).getName()).isEqualTo("product3");

    }

    @Test
    public void should_cut_1_prod_when_one_prod_is_deleted() throws Exception {
        UserModel userModel = userProdModel();
        userDao.save(userModel);
        Long userId = userModel.getId();

        userModel.getProducts().remove(1);

        User deletedUser = userService.getUser(userId);
        assertThat(deletedUser.getProducts().size()).isEqualTo(1);
    }

}
