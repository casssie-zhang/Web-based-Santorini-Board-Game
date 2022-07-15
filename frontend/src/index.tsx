import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import ChooseCard from './ChooseCard'
import Board from './Board'
import RuleBook from './RuleBook'

ReactDOM.render(
  /** Router for url */
  <Router>
    <Routes>
      <Route path='/' element={<ChooseCard />}> </Route>
      <Route path='/board' element={<Board />} />
      <Route path='/choosegod' element={<ChooseCard />} />
      <Route path='/rulebook' element={<RuleBook />} />
    </Routes>
  </Router>,
  document.getElementById('root')
)
