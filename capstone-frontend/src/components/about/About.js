import React from 'react'
import Header from '../home/Header'
import { Alert, AlertTitle, Link} from '@mui/material'

const About = () => {
    return (
        <div>
            <Header />
            <Alert severity="info">
                <AlertTitle>About</AlertTitle>
                This app was created for chat messaging.
                <p>Allows multiple users, sending and receiving of messages,
                    deletion of messages by administrators.</p>
                <div><strong>Usage</strong></div> Top right is the User Menu. You can navigate to homepage via 'Home'.
                <div>You can navigate to this page via 'About'.</div>
                <div>You can logout via 'Logout'.</div>
                <div>Clicking on chat.app in the top left also returns you to homepage.</div>
                <div><strong>Technologies used
                    </strong></div>
                    <div><Link href="https://reactjs.org/" underline="hover">
                        {'React'}
                    </Link></div>
                    <div><Link href="https://mui.com/" underline="hover">
                        {'mui'}
                    </Link></div>
                    <div><Link href="https://nodejs.org/" underline="hover">
                        {'node.js'}
                    </Link></div>
                    <div><Link href="https://socket.io/" underline="hover">
                        {'socket.io'}
                    </Link></div>
                    <div>Java </div>   
                    <div>JUnit testing for Java</div>
                    <div>Java Web Tokens</div>
                    <div>MySQL </div>
                    <div>AWS Elastic Beanstalk</div>
            </Alert> 
        </div>
    )
}

export default About