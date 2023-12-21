package com.hy.service.impl;

import cn.hutool.Hutool;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.hy.dto.SelectUsersByphoneResultDto;
import com.hy.entity.Person;
import com.hy.entity.SysPersonPhoneEncrypt;
import com.hy.mapper.PersonMapper;
import com.hy.mapper.SysPersonPhoneEncryptMapper;
import com.hy.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * 对加密数据进行模糊查询的原理：
 * 通过对查询字段进行分词， 将加密后的分词存储到中间表里， 关联主表的id
 * 在查询时， 将关键字进行加密， 然后根据加密的关键字去中间表找到主表的id，以此进行模糊查询
 * 但是对关键字的长度有要求， 比如中间表对字段每4个长度进行分词， 那么在查询的时候也应该是4个字段
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private SysPersonPhoneEncryptMapper encryptMapper;


    @Override
    public Person regist(Person person) {
        personMapper.insert(person);
        // 对电话号码进行解密
        // String phone = this.decrypt(person.getPhone());

        String phoneKeyWords = this.phoneKeywords(person.getPhone());
        SysPersonPhoneEncrypt sysPersonPhoneEncrypt = new SysPersonPhoneEncrypt(Math.toIntExact(person.getUserId()), phoneKeyWords);
        encryptMapper.insert(sysPersonPhoneEncrypt);
        return person;
    }

    @Override
    public List<SelectUsersByphoneResultDto> getPersonList(String phoneVal) {
        if (phoneVal != null) {
            String encrypt = this.encrypt(phoneVal);
            return personMapper.selectUsersByphone(encrypt);
        }
        return null;
    }

    private String phoneKeywords(String phone) {
        String keywords = this.keywords(phone, 4);
        System.out.println(keywords.length());
        return keywords;
    }

    private String keywords(String word, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int end = i + len;
            String sub1 = word.substring(i, end);
            sb.append(this.encrypt(sub1));
            if (end == word.length()) {
                break;
            }
        }
        return sb.toString();
    }

    public String encrypt(String val) {
        // 这里特别注意一下，对称加密是根据密钥进行加密和解密的，加密和解密的密钥是相同的，一旦泄漏，就无秘密可言.
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), "hzbb-czpp".getBytes()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
        return aes.encryptBase64(val);
    }

    public String decrypt(String val) {
        // 这里特别注意一下，对称加密是根据密钥进行加密和解密的，加密和解密的密钥是相同的，一旦泄漏，就无秘密可言.
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), "hzbb-czpp".getBytes()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
        return aes.decryptStr(val);
    }
}
