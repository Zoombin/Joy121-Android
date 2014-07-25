package gejw.android.quickandroid.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.util.Log;

public class QPermissonUtils {

	public static class Permisson {
		public String permName;
		public String permContent;
	}

	public static List<Permisson> getPermisson(Context context) {
		return getPermisson(context, context.getPackageName());
	}

	public static List<Permisson> getPermisson(Context context,
			String packagename) {
		List<Permisson> permissons = new ArrayList<QPermissonUtils.Permisson>();
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pkgInfo = pm.getPackageInfo(packagename,
					PackageManager.GET_PERMISSIONS);// 通过包名，返回包信息
			String sharedPkgList[] = pkgInfo.requestedPermissions;// 得到权限列表
			if (sharedPkgList == null)
				return permissons;

			for (int i = 0; i < sharedPkgList.length; i++) {
				String permName = sharedPkgList[i];

//				PermissionInfo tmpPermInfo = pm.getPermissionInfo(permName, 0);// 通过permName得到该权限的详细信息
				// PermissionGroupInfo pgi = pm.getPermissionGroupInfo(
				// tmpPermInfo.group, 0);// 权限分为不同的群组，通过权限名，我们得到该权限属于什么类型的权限。
				Permisson permisson = new Permisson();
				permisson.permName = permName;
//				permisson.permContent = tmpPermInfo.loadLabel(pm) + "\n"
//						+ tmpPermInfo.loadDescription(pm) + "\n";

				permissons.add(permisson);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return permissons;
	}

	/**
	 * @param context
	 * @return hashmap key-->权限 value-->文件名
	 */
	public static HashMap<String, List<String>> getAllPermisson(Context context) {
		getAllApps(context);
		HashMap<String, List<String>> map_Permisson = new HashMap<String, List<String>>();

		List<String> apps = getAllApps(context);
		for (int i = 0; i < apps.size(); i++) {
			String app = apps.get(i);
			List<Permisson> permissons = getPermisson(context, app);
			for (Permisson permisson : permissons) {
				if (map_Permisson.containsKey(permisson.permName)) {
					// 如果存在
					map_Permisson.get(permisson.permName).add(apps.get(i));
				} else {
					// 不存在
					List<String> list = new ArrayList<String>();
					map_Permisson.put(permisson.permName, list);
					map_Permisson.get(permisson.permName).add(apps.get(i));

				}
			}
		}
		apps.clear();
		apps = null;
		return map_Permisson;
	}

	/**
	 * 根据包名 获取包信息
	 * 
	 * @param context
	 * @param packagename
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context, String packagename) {
		PackageManager pm = context.getPackageManager();
		PackageInfo pkgInfo = null;
		try {
			pkgInfo = pm.getPackageInfo(packagename,
					PackageManager.GET_PERMISSIONS);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 通过包名，返回包信息
		return pkgInfo;
	}

	/**
	 * 获取所有安装
	 * 
	 * @param context
	 * @return
	 */
	private static List<String> getAllApps(Context context) {
		List<String> apps = new ArrayList<String>();
		PackageManager pManager = context.getPackageManager();
		// 获取手机内所有应用
		List<PackageInfo> packlist = pManager.getInstalledPackages(0);
		for (int i = 0; i < packlist.size(); i++) {
			PackageInfo pak = (PackageInfo) packlist.get(i);
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0)
				apps.add(pak.packageName);
		}
		return apps;
	}
}
