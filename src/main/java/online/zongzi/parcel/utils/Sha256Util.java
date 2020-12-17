package online.zongzi.parcel.utils;

/**
 * @Author: zongzi
 * @Date: 2020/12/17
 * @Description:
 **/

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
public final class Sha256Util {

    /**
     *
     * @return 生成密码需要的盐
     */
    public static String getSalt(){
        return RandomStringUtils.randomAlphabetic(64);
    }

    /**
     *
     * @param salt
     * @param pwdPlain
     * @return sha256
     */
    public static String sha256(String salt,String pwdPlain){
        return Hashing.sha256().newHasher().putString(salt + pwdPlain, Charsets.UTF_8).hash().toString();
    }
}