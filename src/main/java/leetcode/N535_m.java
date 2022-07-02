package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/encode-and-decode-tinyurl/
 * TinyURL 的加密与解密
 * TinyURL 是一种 URL 简化服务，
 * 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk 。
 * 请你设计一个类来加密与解密 TinyURL 。
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 *
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *
 * 思路 哈希表
 *
 */
public class N535_m {

    int id = 0;
    Map<Integer, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        id++;
        map.put(id, longUrl);
        return "http://tinyurl.com/" + id;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int find = Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""));
        return map.get(find);
    }

}
