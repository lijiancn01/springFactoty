def collect=["a","b","c"]
collect.add(1);
collect<<"come on";
collect[collect.size()]=100.0
//println collect[collect.size()-1]
//println collect[5]
//for(i in 0..collect.size()){
//	println collect[i]
//}
println collect[-1]      //索引其倒数第1个元素
println collect[-2]      //索引其倒数第2个元素
collect=collect+5        //在集合中添加元素5
println collect[collect.size()-1]
collect=collect-'a'         //在集合中减去元素a(第1个)
println collect[0]          //现在第1个元素变成b了
collect=collect-collect[0..4]   //把集合中的前5个元素去掉
println collect[0]   //现在集合中仅有一个元素，即原来的最后一个元素
println collect[-1]  //也可以用负索引，证明最后一个元素就是第一个元素

