map.each({key,value->    //key,value两个参数用于接受每个元素的键/值
println "$key:$value"})
map.each{println it}     //it是一个关键字，代表map集合的每个元素
map.each({ println it.getKey()+"-->"+it.getValue()})

