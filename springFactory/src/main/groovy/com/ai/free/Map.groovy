def map=['name':'john','age':14,'sex':'boy']
map=map+['weight':25]       //添加john的体重
map.put('length',1.27)      //添加john的身高
map.father='Keller'         //添加john的父亲
//println map['father']       //通过key作为下标索引
//println map.length          //通过key作为成员名索引

//map.each({key,value->    //key,value两个参数用于接受每个元素的键/值
//println "$key:$value"})
//map.each{println it}     //it是一个关键字，代表map集合的每个元素
//map.each({ println it.getKey()+"-->"+it.getValue()})

def say={word->	 println "Hi,$word!"}
say('groovy')
say.call('groovy&grails')

