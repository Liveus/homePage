package com.liveus.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/13 17:21
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class JvmUtils {

    private static final int SLEEP_TIME = 1000 * 60 * 9;
    private static final int FAULT_LENGTH = 10;
    private static final int PERCENT = 100;
    private static final int CPU_TIME = 5000;
    private static String pid = "";

    @Test
    public void test(){
        getJvmInfo();
    }

    public static Map<String,Object> getJvmInfo(){
        Map<String,Object> map = new HashMap<>();
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 操作系统
        map.put("operateSystem",System.getProperty("os.name"));
        System.out.println(map.get("operateSystem"));
        // 系统cpu负载
        map.put("systemCpuLoad", osmxb.getSystemCpuLoad());
        System.out.println(map.get("systemCpuLoad"));
        // 获取jvm线程负载
        map.put("ProcessCpuLoad",osmxb.getProcessCpuLoad());
        System.out.println(map.get("ProcessCpuLoad"));
        // 获取总的物理内存
        map.put("TotalMemorySize",osmxb.getTotalPhysicalMemorySize()/1024);
        System.out.println(map.get("TotalMemorySize"));
        // 获取剩余的物理内存
        map.put("FreePhysicalMemorySize",osmxb.getFreePhysicalMemorySize()/1024);
        System.out.println(map.get("FreePhysicalMemorySize"));
        // 获取已使用的物理内存
        map.put("UsedMemory",(osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024);
        System.out.println(map.get("UsedMemory"));

        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        map.put("Jvm总内存",rt.totalMemory() / byteToMb + " MB");
        System.out.println(map.get("Jvm总内存"));
        map.put("Jvm剩余内存",rt.freeMemory() / byteToMb + " MB");
        System.out.println(map.get("Jvm剩余内存"));
        map.put("Jvm最大内存",rt.maxMemory() / byteToMb + " MB");
        System.out.println(map.get("Jvm最大内存"));
        map.put("Jvm已用内存",rt.totalMemory() / byteToMb-rt.freeMemory() / byteToMb + " MB");
        System.out.println(map.get("Jvm已用内存"));
/*        if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
            map.put("JVM PID",getJvmPIDOnWindows());    //JVM PID
            map.put("Network",getNetworkThroughputForWindows());    //网络带宽
            map.put("Cpu rate",getCPURateForWindows()); //jvm cpu 使用率
            map.put("Thread count",getThreadCountForWindows()); //jvm cpu 使用率

        }else {
            map.put("JVM PID",getJvmPIDOnLinux());  //JVM PID
            map.put("Network",getNetworkThroughputForLinux());  //网络带宽
            map.put("Cpu rate",getCPURateForLinux());   //jvm cpu 使用率
            map.put("Thread count",getThreadCountForLinux()); //jvm cpu 使用率
        }*/
        return map;
    }

    /**
     * windows环境下获取JVM的PID
     */
    private static String getJvmPIDOnWindows() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        pid = runtime.getName().split("@")[0];
        return "PID of JVM:" + pid;
    }

    /**
     * linux环境下获取JVM的PID
     */
    private static String getJvmPIDOnLinux() {
        String command = "pidof java";
        BufferedReader in = null;
        Process pro = null;
        try {
            pro = Runtime.getRuntime().exec(new String[] { "sh", "-c", command });
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringTokenizer ts = new StringTokenizer(in.readLine());
            pid = ts.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "PID of JVM:" + pid;
    }

    /**
     * 获取windows环境下JVM的cpu占用率
     * 
     * @return
     */
    public static String getCPURateForWindows() {
        try {
            String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe  process "
                    + "  get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPU_TIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                long cpuRate = PERCENT * (busytime) / (busytime + idletime);
                if (cpuRate > 100) {
                    cpuRate = 100;
                } else if (cpuRate < 0) {
                    cpuRate = 0;
                }
                return String.valueOf(PERCENT * (busytime) / (busytime + idletime));


            } else {
                return "0.0";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "0.0";
        }
    }

    /**
     * 获取linux环境下JVM的cpu占用率
     * 
     * @return
     */
    public static String getCPURateForLinux() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader brStat = null;
        StringTokenizer tokenStat = null;
        String user = "";
        String linuxVersion = System.getProperty("os.version");
        String pid = "";
        try {
            System.out.println("Linux版本: " + linuxVersion);
            Process process = Runtime.getRuntime().exec(new String[] { "sh", "-c", "top -b -p " + pid });
            try {
                // top命令默认3秒动态更新结果信息，让线程睡眠5秒以便获取最新结果
                Thread.sleep(CPU_TIME);
                is = process.getInputStream();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isr = new InputStreamReader(is);
            brStat = new BufferedReader(isr);
            if (linuxVersion.equals("2.4")) {
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                tokenStat = new StringTokenizer(brStat.readLine());
                tokenStat.nextToken();
                tokenStat.nextToken();
                user = tokenStat.nextToken();
                tokenStat.nextToken();
                String system = tokenStat.nextToken();
                tokenStat.nextToken();
                String nice = tokenStat.nextToken();
                System.out.println(user + " , " + system + " , " + nice);
                user = user.substring(0, user.indexOf("%"));
                system = system.substring(0, system.indexOf("%"));
                nice = nice.substring(0, nice.indexOf("%"));
                float userUsage = Float.parseFloat(user);
                float systemUsage = Float.parseFloat(system);
                float niceUsage = Float.parseFloat(nice);
                return String.valueOf((userUsage + systemUsage + niceUsage) / 100);
            } else {
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                tokenStat = new StringTokenizer(brStat.readLine());
                tokenStat.nextToken();
                String userUsage = tokenStat.nextToken(); // 用户空间占用CPU百分比
                user = userUsage.substring(0, userUsage.indexOf("%"));
                process.destroy();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            freeResource(is, isr, brStat);
            return "100";
        } finally {
            freeResource(is, isr, brStat);
        }
        return user; // jvm cpu占用率
    }

    /**
     * 
     * 读取CPU信息
     * 
     * @param proc
     * @return
     * 
     */
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULT_LENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            // Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation
                String caption = substring(line, capidx, cmdidx - 1).trim();
                String cmd = substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.indexOf("javaw.exe") >= 0) {
                    continue;
                }
                // log.info("line="+line);
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    idletime += Long.valueOf(substring(line, kmtidx, rocidx - 1).trim()).longValue();
                    idletime += Long.valueOf(substring(line, umtidx, wocidx - 1).trim()).longValue();
                    continue;
                }


                kneltime += Long.valueOf(substring(line, kmtidx, rocidx - 1).trim()).longValue();
                usertime += Long.valueOf(substring(line, umtidx, wocidx - 1).trim()).longValue();
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
     * 
     * @param src
     *            要截取的字符串
     * @param start_idx
     *            开始坐标（包括该坐标)
     * @param end_idx
     *            截止坐标（包括该坐标）
     * @return
     */
    private static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        String tgt = "";
        for (int i = start_idx; i <= end_idx; i++) {
            tgt += (char) b[i];
        }
        return tgt;
    }

    private static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br) {
        try {
            if (is != null)
                is.close();
            if (isr != null)
                isr.close();
            if (br != null)
                br.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    /**
     * 获取Windows环境下网口的上下行速率
     * 
     * @return
     */
    public static String getNetworkThroughputForWindows() {
        Process pro1 = null;
        Process pro2 = null;
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        String rxPercent = "";
        String txPercent = "";
        JSONObject jsonObject = new JSONObject();
        try {
            String command = "netstat -e";
            pro1 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String result1[] = readInLine(input, "windows");
            Thread.sleep(SLEEP_TIME);
            pro2 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String result2[] = readInLine(input, "windows");
            rxPercent = formatNumber((Long.parseLong(result2[0]) - Long.parseLong(result1[0]))
                    / (float) (1024 * 1024 * (SLEEP_TIME / 1000))); // 上行速率(MB/s)
            txPercent = formatNumber((Long.parseLong(result2[1]) - Long.parseLong(result1[1]))
                    / (float) (1024 * 1024 * (SLEEP_TIME / 1000))); // 下行速率(MB/s)
            input.close();
            pro1.destroy();
            pro2.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("rxPercent", rxPercent); // 下行速率
        jsonObject.put("txPercent", txPercent); // 上行速率
        return JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
    }


    /**
     * 获取Linux环境下网口的上下行速率
     * 
     * @return
     */
    public static String getNetworkThroughputForLinux() {
        Process pro1 = null;
        Process pro2 = null;
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        String rxPercent = "";
        String txPercent = "";
        JSONObject jsonObject = new JSONObject();
        try {
            String command = "watch ifconfig";
            pro1 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String result1[] = readInLine(input, "linux");
            Thread.sleep(SLEEP_TIME);
            pro2 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String result2[] = readInLine(input, "linux");
            rxPercent = formatNumber((Long.parseLong(result2[0]) - Long.parseLong(result1[0]))
                    / (float) (1024 * 1024 * (SLEEP_TIME / 1000))); // 下行速率(MB/s)
            txPercent = formatNumber((Long.parseLong(result2[1]) - Long.parseLong(result1[1]))
                    / (float) (1024 * 1024 * (SLEEP_TIME / 1000))); // 上行速率(MB/s)
            input.close();
            pro1.destroy();
            pro2.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("rxPercent", rxPercent); // 下行速率
        jsonObject.put("txPercent", txPercent); // 上行速率
        return JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
    }

    /**
     * 获取网口上下行速率
     * 
     * @param input
     * @return
     */
    private static String[] readInLine(BufferedReader input, String osType) {
        String rxResult = "";
        String txResult = "";
        StringTokenizer tokenStat = null;
        try {
            // 获取linux环境下的网口上下行速率
            if (osType.equals("linux")) {
                String result[] = input.readLine().split(" ");
                int j = 0, k = 0;
                for (int i = 0; i < result.length; i++) {
                    if (result[i].indexOf("RX") != -1) {
                        j++;
                        if (j == 2) {
                            rxResult = result[i + 1].split(":")[1];
                        }
                    }
                    if (result[i].indexOf("TX") != -1) {
                        k++;
                        if (k == 2) {
                            txResult = result[i + 1].split(":")[1];
                            break;
                        }
                    }
                }
            } else { // 获取windows环境下的网口上下行速率
                input.readLine();
                input.readLine();
                input.readLine();
                input.readLine();
                tokenStat = new StringTokenizer(input.readLine());
                tokenStat.nextToken();
                rxResult = tokenStat.nextToken();
                txResult = tokenStat.nextToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String arr[] = { rxResult, txResult };
        return arr;
    }

    /**
     * 格式化浮点数(float 和 double)，保留两位小数
     * 
     * @param obj
     * @return
     */
    private static String formatNumber(Object obj) {
        String result = "";
        if (obj.getClass().getSimpleName().equals("Float")) {
            result = new Formatter().format("%.2f", (float) obj).toString();
        } else if (obj.getClass().getSimpleName().equals("Double")) {
            result = new Formatter().format("%.2f", (double) obj).toString();
        }
        return result;
    }

    /**
     * 获取Linux环境下JVM的线程数
     * 
     * @return
     */
    public static int getThreadCountForLinux() {
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        String command = "top -b -n 1 -H -p " + pid;
        BufferedReader in = null;
        int result = 0;
        try {
            pro = r.exec(new String[] { "sh", "-c", command });
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            in.readLine();
            StringTokenizer ts = new StringTokenizer(in.readLine());
            ts.nextToken();
            result = Integer.parseInt(ts.nextToken());
            in.close();
            pro.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取Windows环境下JVM的线程数
     * 
     * @return
     */
    public static int getThreadCountForWindows() {
        String command = "wmic process " + pid + "  list brief";
        int count = 0;
        BufferedReader in = null;
        try {
            Process pro = Runtime.getRuntime().exec(command);
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            // testGetInput(in);
            in.readLine();
            in.readLine();
            StringTokenizer ts = new StringTokenizer(in.readLine());
            int i = 1;
            while (ts.hasMoreTokens()) {
                i++;
                ts.nextToken();
                if (i == 5) {
                    count = Integer.parseInt(ts.nextToken());
                }
            }
            in.close();
            pro.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
