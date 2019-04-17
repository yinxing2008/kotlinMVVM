package com.cxyzy.demo.utils.functional

typealias Supplier<T> = () -> T

interface Consumer<T> {

    fun accept(t: T)
}